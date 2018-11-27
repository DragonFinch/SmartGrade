package com.school.nfcard.entity;

import java.util.List;

/**
 * 此类的作用：XXXXXX
 * <p>
 * Created by Liu on 2018/10/23.
 */
public class ClassContent {


    /**
     * success : 1
     * errormsg : 0
     * data : {"class_slogans":"激扬青春、花样年华、怎能虚度、也曾跌倒、\r也曾失败、怎能放弃。","class_content":"十三级六班是一个充满欢乐的班集体，源于对知识的渴求，生于对梦想的期望，我们努力，我们永不服输，在一次次考验中，我们手拉手，心连心，闯过一道道坎坷之路，无论失败或成功，都是我们的骄傲，风雨过后，我们为集体而战，为自己而战，为不留遗憾而战。","class_motto":"感恩,责任,努力,收获","class_atmosphere":"团结,勇敢,奋进,和谐","class_dynamics":[{"class_content":"王小刚同学获得学校三好学生奖"},{"class_content":"高淼同学在学校跳绳比赛中获得第一名"},{"class_content":"我们班在校园大扫除中，获得第一名"}]}
     */

    private String success;
    private String errormsg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * class_slogans : 激扬青春、花样年华、怎能虚度、也曾跌倒、也曾失败、怎能放弃。
         * class_content : 十三级六班是一个充满欢乐的班集体，源于对知识的渴求，生于对梦想的期望，我们努力，我们永不服输，在一次次考验中，我们手拉手，心连心，闯过一道道坎坷之路，无论失败或成功，都是我们的骄傲，风雨过后，我们为集体而战，为自己而战，为不留遗憾而战。
         * class_motto : 感恩,责任,努力,收获
         * class_atmosphere : 团结,勇敢,奋进,和谐
         * class_dynamics : [{"class_content":"王小刚同学获得学校三好学生奖"},{"class_content":"高淼同学在学校跳绳比赛中获得第一名"},{"class_content":"我们班在校园大扫除中，获得第一名"}]
         */

        private String class_slogans;
        private String class_content;
        private String class_motto;
        private String class_atmosphere;
        private List<ClassDynamicsBean> class_dynamics;

        public String getClass_slogans() {
            return class_slogans;
        }

        public void setClass_slogans(String class_slogans) {
            this.class_slogans = class_slogans;
        }

        public String getClass_content() {
            return class_content;
        }

        public void setClass_content(String class_content) {
            this.class_content = class_content;
        }

        public String getClass_motto() {
            return class_motto;
        }

        public void setClass_motto(String class_motto) {
            this.class_motto = class_motto;
        }

        public String getClass_atmosphere() {
            return class_atmosphere;
        }

        public void setClass_atmosphere(String class_atmosphere) {
            this.class_atmosphere = class_atmosphere;
        }

        public List<ClassDynamicsBean> getClass_dynamics() {
            return class_dynamics;
        }

        public void setClass_dynamics(List<ClassDynamicsBean> class_dynamics) {
            this.class_dynamics = class_dynamics;
        }

        public static class ClassDynamicsBean {
            /**
             * class_content : 王小刚同学获得学校三好学生奖
             */

            private String class_content;

            public String getClass_content() {
                return class_content;
            }

            public void setClass_content(String class_content) {
                this.class_content = class_content;
            }
        }
    }
}
