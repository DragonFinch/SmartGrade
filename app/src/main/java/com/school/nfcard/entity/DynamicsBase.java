package com.school.nfcard.entity;

import java.util.List;

/**
 * 此类的作用：班级动态
 * <p>
 * Created by Liu on 2018/10/23.
 */
public class DynamicsBase {

    /**
     * success : 1
     * errormsg : 0
     * data : [{"class_content":"王小刚同学获得学校三好学生奖"},{"class_content":"高淼同学在学校跳绳比赛中获得第一名"},{"class_content":"我们班在校园大扫除中，获得第一名"}]
     */

    private String success;
    private String errormsg;
    private List<Dynamics> data;

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

    public List<Dynamics> getData() {
        return data;
    }

    public void setData(List<Dynamics> data) {
        this.data = data;
    }
}
