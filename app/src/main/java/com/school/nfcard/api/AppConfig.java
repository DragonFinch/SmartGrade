package com.school.nfcard.api;

import android.os.Environment;

import java.io.File;

/**
 * 此类作用：App的基本配置
 * <p>
 * 作者：LiuHW
 * <p>
 * 邮箱：zixuan06010@126.com
 */

public class AppConfig {
    /****
     * 数据库的表名
     */
    public static String TABLENAME = "new_school.db";
    /****
     * SD卡文件位置
     */
    public static final String SDKA = Environment.getExternalStorageDirectory() + File.separator;
    /**
     * 下载的文件存放的根目录
     */
    public static final String FILE_DOWNLOAD = SDKA + "newschool/parent/";

    /**
     * crash放置的目录
     */
    public static final String DOC = FILE_DOWNLOAD + "crash/";

    /****
     * 声音存放的位置
     */
    public static final String VOICE = FILE_DOWNLOAD + "voice/";

    /****
     * 图片存放的位置
     */
    public static final String IAMGE = FILE_DOWNLOAD + "image/";

    /****
     * mp2声音存放的位置
     */
    public static final String MP3VOICE = VOICE + "mps/";
    /**
     * 默认 SharePreferences文件名.
     */
    public static String SHARED_PATH = "app_share";
    /****
     * 加密存储SharePreferences文件名.
     */
    public static String SYN_SHARED_PATH = "syn_app_share";

    /****
     * 默认的电话
     */
    public static String PHONE = "13800138000";

    /****
     * 融云初始化成功的标识
     */
    public static boolean RONGTAG = false;

    /****
     * 默认的头像
     */
    public static String defaultHead = "http://";


    /****
     * 默认上传文件类型  图片
     */
    public static String FILEIMG = "img";
    /****
     * 默认上传文件类型  声音
     */
    public static String FILEVOICE = "voice";
}
