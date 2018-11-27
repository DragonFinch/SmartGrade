package com.school.nfcard.entity;

/**
 * 此类的作用：Object  Modle
 * <p>
 * Created by Liu on 2017/11/21.
 */

public class BaseObjectModle<T> extends BaseModle {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
