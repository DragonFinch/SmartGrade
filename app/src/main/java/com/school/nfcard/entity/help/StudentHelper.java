package com.school.nfcard.entity.help;

import com.school.nfcard.entity.Student;
import com.school.nfcard.greendao.StudentDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类的作用：XXXXXX
 * <p>
 * Created by Liu on 2018/8/18.
 */
public class StudentHelper {

    private static StudentDao getDao() {
        return GreenDaoManager.getInstance().getmDaoSession().getStudentDao();
    }

    public static Long insert(Student student) {
        try {
            return getDao().insert(student);
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }


    public static void deleteAll() {
        getDao().deleteAll();
    }


    public static List<Student> getStudent(String cardNo) {
        List<Student> students = new ArrayList<>();
        try {
            students.clear();
            students.addAll(getDao().queryBuilder().where(StudentDao.Properties.Cardno.eq(cardNo)).list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}
