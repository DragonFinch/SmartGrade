package com.school.nfcard.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 此类的作用：班级信息
 * <p>
 * Created by Liu on 2018/9/13.
 */

@Entity
public class ClassInfo {
    @Id
    private String id;
    private String name;
    private String schoolid;
    @Generated(hash = 1213348705)
    public ClassInfo(String id, String name, String schoolid) {
        this.id = id;
        this.name = name;
        this.schoolid = schoolid;
    }
    @Generated(hash = 295356596)
    public ClassInfo() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSchoolid() {
        return this.schoolid;
    }
    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }

}
