package com.school.nfcard.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 此类的作用：XXXXXX
 * <p>
 * Created by Liu on 2018/9/14.
 */
public class LoginRes {


    /**
     * SUCCESS : 登陆成功
     * data : [{"name":"包钢实验二小西校区","id":"22","class":[{"name":"13级二班","id":"60","schoolid":"22"},{"name":"13级一班","id":"59","schoolid":"22"},{"name":"13级三班","id":"61","schoolid":"22"},{"name":"13级四班","id":"62","schoolid":"22"},{"name":"13级五班","id":"63","schoolid":"22"},{"name":"13级六班","id":"64","schoolid":"22"},{"name":"13级七班","id":"65","schoolid":"22"},{"name":"13级八班","id":"66","schoolid":"22"},{"name":"13级九班","id":"67","schoolid":"22"},{"name":"13级十班","id":"68","schoolid":"22"},{"name":"12级二班","id":"81","schoolid":"22"},{"name":"12级一班","id":"80","schoolid":"22"},{"name":"12级三班","id":"82","schoolid":"22"},{"name":"12级四班","id":"83","schoolid":"22"},{"name":"12级五班","id":"84","schoolid":"22"},{"name":"12级六班","id":"85","schoolid":"22"},{"name":"12级七班","id":"86","schoolid":"22"},{"name":"12级八班","id":"87","schoolid":"22"},{"name":"12级九班","id":"88","schoolid":"22"},{"name":"12级十班","id":"89","schoolid":"22"}]},{"name":"包钢实验二小东校区","id":"17","class":[{"name":"14级1班","id":"28","schoolid":"17"},{"name":"14级2班","id":"29","schoolid":"17"},{"name":"14级3班","id":"30","schoolid":"17"},{"name":"14级4班","id":"31","schoolid":"17"},{"name":"14级5班","id":"32","schoolid":"17"},{"name":"14级6班","id":"33","schoolid":"17"},{"name":"14级7班","id":"34","schoolid":"17"},{"name":"14级8班","id":"35","schoolid":"17"},{"name":"14级9班","id":"36","schoolid":"17"},{"name":"14级10班","id":"37","schoolid":"17"},{"name":"15级1班","id":"38","schoolid":"17"},{"name":"15级2班","id":"39","schoolid":"17"},{"name":"15级3班","id":"40","schoolid":"17"},{"name":"15级4班","id":"41","schoolid":"17"},{"name":"15级5班","id":"42","schoolid":"17"},{"name":"15级6班","id":"43","schoolid":"17"},{"name":"15级7班","id":"44","schoolid":"17"},{"name":"15级8班","id":"45","schoolid":"17"},{"name":"15级9班","id":"46","schoolid":"17"},{"name":"15级10班","id":"47","schoolid":"17"},{"name":"16级1班","id":"48","schoolid":"17"},{"name":"16级2班","id":"49","schoolid":"17"},{"name":"16级3班","id":"50","schoolid":"17"},{"name":"16级4班","id":"51","schoolid":"17"},{"name":"16级5班","id":"52","schoolid":"17"},{"name":"16级6班","id":"53","schoolid":"17"},{"name":"16级7班","id":"54","schoolid":"17"},{"name":"16级8班","id":"55","schoolid":"17"},{"name":"16级9班","id":"56","schoolid":"17"},{"name":"16级10班","id":"57","schoolid":"17"}]},{"name":"包头市卜尔汉图小学","id":"24","class":[{"name":"16级1班","id":"92","schoolid":"24"},{"name":"16级2班","id":"93","schoolid":"24"},{"name":"16级3班","id":"94","schoolid":"24"},{"name":"15级1班","id":"95","schoolid":"24"},{"name":"15级2班","id":"96","schoolid":"24"},{"name":"15级3班","id":"97","schoolid":"24"},{"name":"14级1班","id":"98","schoolid":"24"},{"name":"14级2班","id":"99","schoolid":"24"},{"name":"14级3班","id":"100","schoolid":"24"},{"name":"13级1班","id":"101","schoolid":"24"},{"name":"13级2班","id":"102","schoolid":"24"},{"name":"12级1班","id":"103","schoolid":"24"},{"name":"12级2班","id":"104","schoolid":"24"},{"name":"17级1班","id":"105","schoolid":"24"}]}]
     */

    @SerializedName("SUCCESS")
    private String success;
    private String errormsg;
    private List<SchoolInfo> data;

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

    public List<SchoolInfo> getData() {
        return data;
    }

    public void setData(List<SchoolInfo> data) {
        this.data = data;
    }
}
