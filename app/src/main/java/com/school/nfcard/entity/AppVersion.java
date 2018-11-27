package com.school.nfcard.entity;

/**
 * 此类的作用：检测程序版本号
 * <p>
 * Created by LiuHW on 2017/8/14.
 * <p>
 * 邮箱：zixuan06010@126.com
 */

public class AppVersion {


    /**
     * success : 1
     * errormsg : 0
     * data : {"ipv6":"app.xinzhidi.com","pic_list":["/a.png","/a.png","/a.png"],"must_download":"0","version":"2.0.4","description":"更新吧，亲，更新了就更好用了！","ios_p_must_download":"1","ios_t_must_download":"1","android_p_must_download":"0","android_t_must_download":"0","ios_p_version":"2.0.4","ios_t_version":"1.0.8","android_p_version":"3.0.0","android_t_version":"4.0.0","ios_p_description":"a","ios_t_description":"b","android_p_description":"c","android_t_description":"d","last_android_download_url":"/uploadfiles/apk/xinxiaoyuan2.0.4.apk","t_last_android_download_url":"adefawe","last_ios_download_url":"wfaewf","t_last_ios_download_url":"wfaef"}
     */

    private String success;
    private String errormsg;
    private DataBean data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String version;
        private String description;
        private String last_android_download_url;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLast_android_download_url() {
            return last_android_download_url;
        }

        public void setLast_android_download_url(String last_android_download_url) {
            this.last_android_download_url = last_android_download_url;
        }
    }
}
