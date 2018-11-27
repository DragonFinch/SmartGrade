package com.school.nfcard.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.demo.moddle.presenter.HandlerError;
import com.google.gson.Gson;
import com.school.nfcard.camera.FileUtil;
import com.school.nfcard.constant.AppConfig;
import com.school.nfcard.entity.UploadResult;
import com.school.nfcard.presenter.base.BasePresenter;
import com.school.nfcard.presenter.impl.UploadFileContrat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 此类的作用：XXXXXX
 * <p>
 * Created by LiuHW on 2017/8/7.
 */

public class UploadFilePresenter extends BasePresenter<UploadFileContrat.View> implements UploadFileContrat {

    private MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    public UploadFilePresenter(UploadFileContrat.View view) {
        attachView(view);
    }


    @Override
    public void uploadFile(String sign, String path) {
        MEDIA_TYPE_PNG = MediaType.parse("image/png");
        Map<String, String> map = new HashMap<>();
        map.put("sign", "签名文件");
        List<File> files = new ArrayList<>();
        File file = new File(path);
        files.add(file);
        send(map, files);
    }

    @Override
    public void uploadFileOSS(Context context, String path) {
        try {
            final String endpoint = "oss-cn-beijing.aliyuncs.com";
            final String bucketName = "xinxiaoyuan";
            final String accessKeyId = "LTAIwHFjfkKmlGSF";
            final String accessKeySecret = "z1SRQ0yFxfmkUB5TvWvjFUO1t5Re4e";
            OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKeyId, accessKeySecret);
            ClientConfiguration conf = new ClientConfiguration();
            conf.setConnectionTimeout(15 * 1000);
            conf.setSocketTimeout(15 * 1000);
            conf.setMaxConcurrentRequest(8);
            conf.setMaxErrorRetry(2);
            OSSClient ossClient = new OSSClient(context, endpoint, credentialProvider, conf);
            ObjectMetadata objectMeta = new ObjectMetadata();
            objectMeta.setContentType("image/jpeg");
            String name = "classcard/"+System.currentTimeMillis() + ".jpg";
            String url = "https://" + bucketName + "." + endpoint + "/" + name;
            getView().uploadSucess(url);
            PutObjectRequest put = new PutObjectRequest(bucketName, name, path);
            put.setMetadata(objectMeta);
            try {
                PutObjectResult result = ossClient.putObject(put);
                if (result != null && result.getStatusCode() == 200) {
                 //   FileUtil.deleteFile(path);
                }
            } catch (ClientException e) {
                e.printStackTrace();
               // FileUtil.deleteFile(path);
            } catch (ServiceException e) {
                e.printStackTrace();
              //  FileUtil.deleteFile(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
          //  FileUtil.deleteFile(path);
        }
    }

    /**
     * 上传多张图片及参数
     *
     * @param reqUrl  URL地址
     * @param params  参数
     * @param pic_key 上传图片的关键字
     */
    public Observable<String> sendMultipart(final String reqUrl, final Map<String, String> params, final String pic_key, final List<File> files) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
                multipartBodyBuilder.setType(MultipartBody.FORM);
                if (params != null) {
                    for (String key : params.keySet()) {
                        multipartBodyBuilder.addFormDataPart(key, params.get(key));
                    }
                }
                if (files != null) {
                    for (File file : files) {
                        String name = file.getName();
                        multipartBodyBuilder.addFormDataPart(pic_key + name, name, RequestBody.create(MEDIA_TYPE_PNG, file));
                    }
                }
                RequestBody requestBody = multipartBodyBuilder.build();
                Request.Builder RequestBuilder = new Request.Builder();
                RequestBuilder.url(reqUrl);
                RequestBuilder.post(requestBody);
                Request request = RequestBuilder.build();
                new OkHttpClient().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        subscriber.onError(e);
                        subscriber.onCompleted();
                        call.cancel();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String str = response.body().string();
                        subscriber.onNext(str);
                        subscriber.onCompleted();
                        call.cancel();
                    }
                });
            }
        });
    }


    private void send(Map<String, String> params, List<File> files) {
        String url = AppConfig.FILE_UPLOAD;
        sendMultipart(url, params, "file", files)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        HandlerError.INSTANCE.handlerError(getView(), throwable);
                    }

                    @Override
                    public void onNext(String s) {
                        UploadResult result = new Gson().fromJson(s, UploadResult.class);
                        if (TextUtils.equals("0", result.getErrormsg())) {

                        } else {
                            getView().showErrorMessage(result.getErrormsg());
                        }
                    }
                });
    }
}
