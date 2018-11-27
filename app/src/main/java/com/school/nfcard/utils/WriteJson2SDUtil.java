package com.school.nfcard.utils;

import android.os.Environment;

import com.school.nfcard.api.AppConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 此类作用：往SD卡中写入数据
 * <p>
 * 作者：LiuHW
 * <p>
 * 邮箱：zixuan06010@126.com
 */

public class WriteJson2SDUtil {
    /*****
     * @param json 需要写的数据
     * @param name 名字
     */
    public static void writeJson(String json, String name) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {// 判断是否存在SD卡
            return;
        }
        File file = new File(AppConfig.FILE_DOWNLOAD + "crash" + File.separator + name + ".txt");
        if (!file.getParentFile().exists()) {// 判断父文件是否存在，如果不存在则创建
            file.getParentFile().mkdirs();
        }
        PrintStream out = null; // 打印流
        try {
            out = new PrintStream(new FileOutputStream(file)); // 实例化打印流对象
            out.print(json.toString()); // 输出数据
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (out != null) { // 如果打印流不为空，则关闭打印流
                out.close();
            }
        }
    }

    /*****
     * 写到SD卡的某个文件夹下
     *
     * @param json
     * @param date
     * @param fileName
     */
    public static void writeJson(String json, String date, String fileName) {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {// 判断是否存在SD卡
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory()
                .toString() + File.separator + fileName + ".txt");
        if (!file.getParentFile().exists()) {// 判断父文件是否存在，如果不存在则创建
            file.getParentFile().mkdirs();
        }
        PrintStream out = null; // 打印流
        try {
            out = new PrintStream(new FileOutputStream(file)); // 实例化打印流对象
            out.print(json.toString() + "\n" + date + "\n"); // 输出数据
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (out != null) { // 如果打印流不为空，则关闭打印流
                out.close();
            }
        }
    }

    /**
     * 读取SD卡中文本文件
     *
     * @param fileName
     *
     * @return
     */
    public static String readSDFile(String fileName) {
        StringBuffer sb = new StringBuffer();
        File file = new File(fileName);
        try {
            FileInputStream fis = new FileInputStream(file);
            int c;
            while ((c = fis.read()) != -1) {
                sb.append((char) c);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
