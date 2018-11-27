package com.school.nfcard.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 此类的作用：XXXXXX
 * <p>
 * Created by Liu on 2018/9/14.
 */
public class Lesson {


    /**
     * success : 返回数据成功
     * data : {"equipment":{"school":"定州一中","class":"12级1班","schoolid":"9","classid":"15"},"lesson":[{"lesson":"语文","lessonid":"1"},{"lesson":"语文","lessonid":"2"},{"lesson":"语文","lessonid":"3"},{"lesson":"语文","lessonid":"4"},{"lesson":"语文","lessonid":"5"},{"lesson":"语文","lessonid":"6"},{"lesson":"语文","lessonid":"7"}],"lasttime":"06:37:00","starttime":"08:35:40","total":2}
     */

    private String success;
    private DataBean data;
    private String errormsg;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public static class DataBean {
        /**
         * equipment : {"school":"定州一中","class":"12级1班","schoolid":"9","classid":"15"}
         * lesson : [{"lesson":"语文","lessonid":"1"},{"lesson":"语文","lessonid":"2"},{"lesson":"语文","lessonid":"3"},{"lesson":"语文","lessonid":"4"},{"lesson":"语文","lessonid":"5"},{"lesson":"语文","lessonid":"6"},{"lesson":"语文","lessonid":"7"}]
         * lasttime : 06:37:00
         * starttime : 08:35:40
         * total : 2
         */

        private EquipmentBean equipment;
        private String lasttime;
        private String starttime;
        private int total;
        private List<LessonBean> lesson;

        public EquipmentBean getEquipment() {
            return equipment;
        }

        public void setEquipment(EquipmentBean equipment) {
            this.equipment = equipment;
        }

        public String getLasttime() {
            return lasttime;
        }

        public void setLasttime(String lasttime) {
            this.lasttime = lasttime;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<LessonBean> getLesson() {
            return lesson;
        }

        public void setLesson(List<LessonBean> lesson) {
            this.lesson = lesson;
        }

        public static class EquipmentBean {
            /**
             * school : 定州一中
             * class : 12级1班
             * schoolid : 9
             * classid : 15
             */

            private String school;
            @SerializedName("class")
            private String classX;
            private String schoolid;
            private String classid;

            public String getSchool() {
                return school;
            }

            public void setSchool(String school) {
                this.school = school;
            }

            public String getClassX() {
                return classX;
            }

            public void setClassX(String classX) {
                this.classX = classX;
            }

            public String getSchoolid() {
                return schoolid;
            }

            public void setSchoolid(String schoolid) {
                this.schoolid = schoolid;
            }

            public String getClassid() {
                return classid;
            }

            public void setClassid(String classid) {
                this.classid = classid;
            }
        }

        public static class LessonBean {
            /**
             * lesson : 语文
             * lessonid : 1
             */

            private String lesson;
            private String lessonid;

            public String getLesson() {
                return lesson;
            }

            public void setLesson(String lesson) {
                this.lesson = lesson;
            }

            public String getLessonid() {
                return lessonid;
            }

            public void setLessonid(String lessonid) {
                this.lessonid = lessonid;
            }
        }
    }
}
