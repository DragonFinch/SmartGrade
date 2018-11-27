package com.school.nfcard.entity.help;

import com.school.nfcard.entity.SchoolInfo;
import com.school.nfcard.entity.Student;
import com.school.nfcard.greendao.SchoolInfoDao;
import com.school.nfcard.greendao.StudentDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类的作用：XXXXXX
 * <p>
 * Created by Liu on 2018/8/18.
 */
public class SchoolHelper {

    private static SchoolInfoDao getDao() {
        return GreenDaoManager.getInstance().getmDaoSession().getSchoolInfoDao();
    }

    public static Long insert(SchoolInfo schoolInfo) {
        try {
            return getDao().insert(schoolInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }


    public static void deleteAll() {
        getDao().deleteAll();
    }


    public static List<SchoolInfo> getSchools() {
        List<SchoolInfo> students = new ArrayList<>();
        try {
            students.clear();
            students.addAll(getDao().queryBuilder().list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}
