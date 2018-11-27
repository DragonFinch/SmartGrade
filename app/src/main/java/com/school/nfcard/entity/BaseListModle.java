package com.school.nfcard.entity;

import java.util.List;

/**
 * 此类的作用：List 集合
 * <p>
 * Created by Liu on 2017/11/21.
 */

public class BaseListModle<T> extends BaseModle {
    private List<T> data;


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
