package com.school.nfcard.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * 此类的作用：Gson 解析工具类
 * <p>
 * Created by Liu on 2018/4/13.
 */
public class GsonUtils {

    private static GsonUtils mInstance;
    private static Gson gson;

    private GsonUtils() {
        gson = new Gson();
    }

    public static GsonUtils getInstance() {
        if (mInstance == null) {
            synchronized (GsonUtils.class) {
                if (mInstance == null) {
                    mInstance = new GsonUtils();
                }
            }
        }
        return mInstance;
    }


    public String classToJson(Object object) {
        JsonElement element = gson.toJsonTree(object);
        return element.toString();
    }

    public <T> T jsonToClass(String json, Class<T> classOfT) {
        T clz = gson.fromJson(json, classOfT);
        return clz;
    }
}
