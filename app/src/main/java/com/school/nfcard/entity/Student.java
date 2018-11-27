package com.school.nfcard.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类的作用：学生信息姓名
 * <p>
 * Created by Liu on 2018/8/13.
 */
@Entity
public class Student {
    /**
     * id : 46
     * schid : 14
     * classid : 25
     * cardno : 2859050823
     * name : 宋大
     * parentname : 宋奶奶
     * device_tokens : Al0EFzRCsOk-bHZYa6aY1tl-XBI2B4tCEkT-nA3RgDvr
     * ios_or_android : android
     */
    @Id(autoincrement = true)
    private Long ids;
    private String id;
    private String schid;
    private String classid;
    private String cardno;
    private String name;
    private String parentname;
    private String device_tokens;
    private String ios_or_android;

    @Generated(hash = 1468979196)
    public Student(Long ids, String id, String schid, String classid, String cardno,
                   String name, String parentname, String device_tokens,
                   String ios_or_android) {
        this.ids = ids;
        this.id = id;
        this.schid = schid;
        this.classid = classid;
        this.cardno = cardno;
        this.name = name;
        this.parentname = parentname;
        this.device_tokens = device_tokens;
        this.ios_or_android = ios_or_android;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public Long getIds() {
        return this.ids;
    }

    public void setIds(Long ids) {
        this.ids = ids;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchid() {
        return this.schid;
    }

    public void setSchid(String schid) {
        this.schid = schid;
    }

    public String getClassid() {
        return this.classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getCardno() {
        return this.cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentname() {
        return this.parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getDevice_tokens() {
        return this.device_tokens;
    }

    public void setDevice_tokens(String device_tokens) {
        this.device_tokens = device_tokens;
    }

    public String getIos_or_android() {
        return this.ios_or_android;
    }

    public void setIos_or_android(String ios_or_android) {
        this.ios_or_android = ios_or_android;
    }


}
