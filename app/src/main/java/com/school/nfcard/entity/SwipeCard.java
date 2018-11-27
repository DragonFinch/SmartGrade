package com.school.nfcard.entity;

/**
 * 此类的作用：XXXXXX
 * <p>
 * Created by Liu on 2018/9/15.
 */
public class SwipeCard {


    /**
     * success : 返回成功
     * data : {"total":2,"img":"/uploadfiles/APP/2017-08-16/5993afef443b2.jpg","name":"薛大","id":"31"}
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

    public DataBean getData() {
        return data;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * total : 2
         * img : /uploadfiles/APP/2017-08-16/5993afef443b2.jpg
         * name : 薛大
         * id : 31
         */

        private int total;
        private String img;
        private String name;
        private String id;
        private int signstud;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getSignstud() {
            return signstud;
        }

        public void setSignstud(int signstud) {
            this.signstud = signstud;
        }
    }
}
