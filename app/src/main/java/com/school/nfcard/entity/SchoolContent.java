package com.school.nfcard.entity;

/**
 * 此类的作用：XXXXXX
 * <p>
 * Created by Liu on 2018/10/23.
 */
public class SchoolContent {


    /**
     * success : 1
     * errormsg : 0
     * data : {"school_content":" 北京市海淀区新动力培训学校的办学理念是：用卓越的教学品质，培养出杰出的学生，让每一位学员真正学有所获。\r\n提倡让学员在学习过程中，享受轻松自由的课堂气氛，加强师生互动，帮助学生提高外语水平，使其能够在商务及社会活动中流利、准确地运用语言。\r\n北京市海淀区新动力培训学校以其独特教学魅力和雄厚师资，吸引了数十万名学员的加入，并使他们逐渐成长为推动中国经济高速发展的商贸精英和留学英才，得到多家中央和北京媒体的专访报道，也获得了社会各界广泛认可。\r\n先后被政府、媒体评为：北京市自律与诚信建设活动先进单位、优秀小语种培训机构、优秀等级类外语培训机构、优秀应试培训机构、诚信办学承诺学校等荣誉称号。","school_img":"https://xinxiaoyuan.oss-cn-beijing.aliyuncs.com/classcard/1538226984075.jpg"}
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
         * school_content :  北京市海淀区新动力培训学校的办学理念是：用卓越的教学品质，培养出杰出的学生，让每一位学员真正学有所获。
         提倡让学员在学习过程中，享受轻松自由的课堂气氛，加强师生互动，帮助学生提高外语水平，使其能够在商务及社会活动中流利、准确地运用语言。
         北京市海淀区新动力培训学校以其独特教学魅力和雄厚师资，吸引了数十万名学员的加入，并使他们逐渐成长为推动中国经济高速发展的商贸精英和留学英才，得到多家中央和北京媒体的专访报道，也获得了社会各界广泛认可。
         先后被政府、媒体评为：北京市自律与诚信建设活动先进单位、优秀小语种培训机构、优秀等级类外语培训机构、优秀应试培训机构、诚信办学承诺学校等荣誉称号。
         * school_img : https://xinxiaoyuan.oss-cn-beijing.aliyuncs.com/classcard/1538226984075.jpg
         */

        private String school_content;
        private String school_img;

        public String getSchool_content() {
            return school_content;
        }

        public void setSchool_content(String school_content) {
            this.school_content = school_content;
        }

        public String getSchool_img() {
            return school_img;
        }

        public void setSchool_img(String school_img) {
            this.school_img = school_img;
        }
    }
}
