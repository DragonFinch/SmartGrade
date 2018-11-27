package com.school.nfcard.entity.help;

import com.school.nfcard.entity.ClassInfo;
import com.school.nfcard.entity.SchoolInfo;
import com.school.nfcard.greendao.ClassInfoDao;
import com.school.nfcard.greendao.SchoolInfoDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类的作用：XXXXXX
 * <p>
 * Created by Liu on 2018/8/18.
 */
public class ClassInfoHelper {

    private static ClassInfoDao getDao() {
        return GreenDaoManager.getInstance().getmDaoSession().getClassInfoDao();
    }

    public static Long insert(ClassInfo classInfo) {
        try {
            return getDao().insert(classInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }


    public static void deleteAll() {
        getDao().deleteAll();
    }


    public static List<ClassInfo> getClassInfo(String schoolid) {
        List<ClassInfo> classInfos = new ArrayList<>();
        try {
            classInfos.clear();
            classInfos.addAll(getDao().queryBuilder().where(ClassInfoDao.Properties.Schoolid.eq(schoolid)).list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classInfos;
    }
}
