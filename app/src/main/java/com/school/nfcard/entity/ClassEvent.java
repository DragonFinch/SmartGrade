package com.school.nfcard.entity;

import com.school.nfcard.ui.card.CardEvent;

import java.util.List;

/**
 * 此类的作用：XXXXXX
 * <p>
 * Created by Liu on 2018/10/26.
 */
public class ClassEvent {


    /**
     * success : 1
     * errormsg : 0
     * data : [{"id":"1","playtime":"2018-11-08","playname":"中国记者节","classid":"26","regdate":"1541606400"},{"id":"2","playtime":"2018-11-22","playname":"感恩节","classid":"26","regdate":"1542816000"},{"id":"3","playtime":"2018-12-01","playname":"艾滋病日","classid":"26","regdate":"1543593600"},{"id":"4","playtime":"2018-12-13","playname":"南京大屠杀纪念日","classid":"26","regdate":"1544630400"},{"id":"5","playtime":"2018-12-20","playname":"澳门回归纪念日","classid":"26","regdate":"1545235200"},{"id":"6","playtime":"2018-12-25","playname":"圣诞节\t","classid":"26","regdate":"1545667200"}]
     */

    private String success;
    private String errormsg;
    private List<CardEvent> data;

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

    public List<CardEvent> getData() {
        return data;
    }

    public void setData(List<CardEvent> data) {
        this.data = data;
    }

    public static class DataBean {
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
}
