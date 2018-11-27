package com.school.nfcard.entity;

import java.util.List;

/**
 * 此类的作用：上传文件的结果
 * <p>
 * Created by LiuHW on 2017/8/8.
 */

public class UploadResult {


    /**
     * success : 1
     * errormsg : 0
     * data : ["/uploadfiles/APP/2017-08-08/59898c95521fd.jpg","/uploadfiles/APP/2017-08-08/59898c95525e9.jpg","/uploadfiles/APP/2017-08-08/59898c955299a.jpg"]
     */

    private String success;
    private String errormsg;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
