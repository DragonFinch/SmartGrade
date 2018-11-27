package com.school.nfcard.api;

import com.school.nfcard.constant.AppConfig;
import com.school.nfcard.presenter.http.RestApi;

import java.util.HashMap;
import java.util.Map;

/**
 * 此类的作用：网络请求工厂
 * <p>
 * Created by Liu on 2018/6/11.
 */
public class ApiFactory {


    private static Map<Class, Object> m_service = new HashMap();

    public static ApiService createApiService() {
        return provideService(ApiService.class);
    }

    private static <T> T provideService(Class cls) {
        Object serv = m_service.get(cls);
        if (serv == null) {
            synchronized (cls) {
                serv = m_service.get(cls);
                if (serv == null) {
                    serv = RestApi.getInstance().create(AppConfig.BASE_URL, cls);
                    m_service.put(cls, serv);
                }
            }
        }
        return (T) serv;
    }


    private static Map<Class, Object> login_service = new HashMap();

    public static ApiService createLoginService() {
        return provideLoginService(ApiService.class);
    }

    private static <T> T provideLoginService(Class cls) {
        Object serv = login_service.get(cls);
        if (serv == null) {
            synchronized (cls) {
                serv = login_service.get(cls);
                if (serv == null) {
                    serv = RestApi.getInstance().create(AppConfig.IP + AppConfig.BASE_URL_Login, cls);
                    login_service.put(cls, serv);
                }
            }
        }
        return (T) serv;
    }


    private static Map<Class, Object> upload_service = new HashMap();

    public static ApiService createUploadService() {
        return provideUploadService(ApiService.class);
    }

    private static <T> T provideUploadService(Class cls) {
        Object serv = upload_service.get(cls);
        if (serv == null) {
            synchronized (cls) {
                serv = upload_service.get(cls);
                if (serv == null) {
                    serv = RestApi.getInstance().create(AppConfig.URL, cls);
                    upload_service.put(cls, serv);
                }
            }
        }
        return (T) serv;
    }


    private static Map<Class, Object> login_service_10 = new HashMap();

    public static ApiService createLogin10Service() {
        return provideLogin10Service(ApiService.class);
    }

    private static <T> T provideLogin10Service(Class cls) {
        Object serv = login_service_10.get(cls);
        if (serv == null) {
            synchronized (cls) {
                serv = login_service_10.get(cls);
                if (serv == null) {
                    serv = RestApi.getInstance().create(AppConfig.IP + AppConfig.BASE_URL_Login_10XT, cls);
                    login_service_10.put(cls, serv);
                }
            }
        }
        return (T) serv;
    }


}
