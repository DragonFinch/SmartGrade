package com.school.nfcard.entity;

/**
 * 此类的作用：XXXXXX
 * <p>
 * Created by Liu on 2018/8/18.
 */
public class ParentMsg {
    private String parentname;
    private String device_tokens;
    private String ios_or_android;

    public ParentMsg(String parentname, String device_tokens, String ios_or_android) {
        this.parentname = parentname;
        this.device_tokens = device_tokens;
        this.ios_or_android = ios_or_android;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getDevice_tokens() {
        return device_tokens;
    }

    public void setDevice_tokens(String device_tokens) {
        this.device_tokens = device_tokens;
    }

    public String getIos_or_android() {
        return ios_or_android;
    }

    public void setIos_or_android(String ios_or_android) {
        this.ios_or_android = ios_or_android;
    }
}
