package com.school.nfcard.ui.card;


public class CardEvent {

    /**
     * id : 1
     * playtime : 2018-11-08
     * playname : 中国记者节
     * classid : 26
     * regdate : 1541606400
     */

    private String id;
    private String playtime;
    private String playname;
    private String classid;
    private String regdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaytime() {
        return playtime;
    }

    public void setPlaytime(String playtime) {
        this.playtime = playtime;
    }

    public String getPlayname() {
        return playname;
    }

    public void setPlayname(String playname) {
        this.playname = playname;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }
}
