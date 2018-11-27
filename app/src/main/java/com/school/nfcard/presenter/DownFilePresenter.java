package com.school.nfcard.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.demo.moddle.presenter.HandlerError;
import com.school.nfcard.api.ApiFactory;
import com.school.nfcard.presenter.base.BasePresenter;
import com.school.nfcard.presenter.http.CallBack;
import com.school.nfcard.presenter.http.TransformUtils;
import com.school.nfcard.presenter.impl.DownFileContract;
import com.school.nfcard.utils.NetUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

/**
 * 此类的作用：下载文件
 * <p>
 * Created by LiuHW on 2017/8/4.
 */

public class DownFilePresenter extends BasePresenter<DownFileContract.View> implements DownFileContract {
    /****
     * SD卡文件位置
     */
    public static final String SDKA = Environment.getExternalStorageDirectory() + File.separator;
    /**
     * 下载的文件存放的根目录
     */
    public static final String FILE_DOWNLOAD = SDKA + "School/NFC/";

    /**
     * crash放置的目录
     */
    public static final String APP = FILE_DOWNLOAD + "APP/";


    private Context context;
    private View view;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String path = msg.getData().getString("filePath");
            Intent i = new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
            context.startActivity(i);
        }
    };

    public DownFilePresenter(DownFileContract.View view) {
        attachView(view);
        this.view = view;
    }

    /****
     * 下载App文件
     *
     * @param context
     * @param url
     */
    @Override
    public void downLoadAppByUrl(final Context context, final String url) {
        if (view != null) {
            this.context = context;
            if (TextUtils.isEmpty(url)) {
                return;
            }
            ApiFactory.createApiService().downFile(url).compose(TransformUtils.<ResponseBody>mainThread()).subscribe(new CallBack<ResponseBody>() {
                @Override
                public void beginStart() {
                    Toast.makeText(context, "正在下载新版本", Toast.LENGTH_SHORT).show();
                    getView().showLoading();
                }

                @Override
                public void successful(ResponseBody response) {
                    getView().hideLoading();
                    writeResponseBodyToDisk(url, response.byteStream());
                }

                @Override
                public void onError(Throwable e) {
                    HandlerError.INSTANCE.handlerError(getView(), e);
                }
            });
        }
    }

    /****
     * 保存文件
     *
     *@param url
     * @param inputStream
     * @return
     */
    private String writeResponseBodyToDisk(String url, InputStream inputStream) {
        try {
            int pos = url.lastIndexOf("/");
            String name = url.substring(pos + 1, url.length());
            String filePath = APP + name;
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                outputStream = new FileOutputStream(file);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                }
                outputStream.flush();
                if (filePath.endsWith(".apk")) {
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("filePath", filePath);
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
                return filePath;
            } catch (IOException e) {
                return null;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return null;
        }
    }
}
