package com.school.nfcard.camera;

/**
 * 此类的作用：XXXXXX
 * <p>
 * Created by Liu on 2018/8/18.
 */

import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    private static final String TAG = "FileUtil";
    private static final File parentPath = Environment.getExternalStorageDirectory();
    private static String storagePath = "";
    private static final String DST_FOLDER_NAME = "School";

    /**
     * 初始化保存路径
     *
     * @return
     */
    private static String initPath() {
        if (storagePath.equals("")) {
            storagePath = parentPath.getAbsolutePath() + "/" + DST_FOLDER_NAME;
            File f = new File(storagePath);
            if (!f.exists()) {
                f.mkdir();
            }
        }
        return storagePath;
    }

    /**
     * 保存Bitmap到sdcard
     *
     * @param b
     */
    public static void saveBitmap(Bitmap b) {
        String path = initPath();
        long dataTake = System.currentTimeMillis();
        String jpegName = path + "/" + dataTake + ".jpg";
        try {
            FileOutputStream fout = new FileOutputStream(jpegName);
            BufferedOutputStream bos = new BufferedOutputStream(fout);
            b.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            if (takePictureLister != null) {
                takePictureLister.getPicturePath(jpegName);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.i(TAG, "saveBitmap:失败");
            e.printStackTrace();
        }
    }


    private static OnTakePictureSuccessLister takePictureLister;

    public static void setTakePictureLister(OnTakePictureSuccessLister lister) {
        takePictureLister = lister;
    }

    public interface OnTakePictureSuccessLister {
        void getPicturePath(String path);
    }


    public static void deleteFile(String filepath) {
        if (!TextUtils.isEmpty(filepath)) {
            File file = new File(filepath);
            file.delete();
        }
    }

}

