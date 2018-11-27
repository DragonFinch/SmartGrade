package com.school.nfcard.constant

/**
 *此类的作用：常量配置
 *
 * Created by Liu on 2018/8/13.
 */
object AppConfig {
    /***** 网络请求地址*/
    const val BASE_URL = "http://app.xinzhidi.com/index.php/"
    /***** 数据库的表名*/
    const val TABLENAME = "nfcard.db"
    /***** 当为0的时候请求正确 */
    const val CODE = "0"
    /***** 正确的请求结果 */
    const val SUCSESS = "success"

    const val BASE_URL_Login = "/api20xp/"

    const val BASE_URL_Login_10XT = "/api10xt/"

    const val IP = "http://class.xinzhidi.com"
    //生产地址
    const val URL = "http://app.xinzhidi.com/index.php/Api20xp/"
    //图片地址
    const val FILE_URL = "http://app.xinzhidi.com"
    //文件上传地址
    const val FILE_UPLOAD = URL + "Functions/uploadfiles"
}


