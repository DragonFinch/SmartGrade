package com.school.nfcard.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类的作用：MODLE 响应的基类
 * <p>
 * Created by Liu on 2017/10/23.
 */

public class BaseModle {

    List<Student> list = new ArrayList<>();

    private String errormsg;

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }


    private void gettest() {
        for(Student student:list){

        }
    }

}
