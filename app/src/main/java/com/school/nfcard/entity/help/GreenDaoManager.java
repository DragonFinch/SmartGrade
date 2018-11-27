package com.school.nfcard.entity.help;

import android.content.Context;

import com.school.nfcard.BaseApplication;
import com.school.nfcard.constant.AppConfig;
import com.school.nfcard.greendao.DaoMaster;
import com.school.nfcard.greendao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * 此类作用：数据库操作管理类
 * <p>
 * 作者：Liu
 * <p>
 */

public class GreenDaoManager {
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private GreenDaoManager() {
        init();
    }

    /**
     * 静态内部类，实例化对象使用
     */
    private static class SingleInstanceHolder {
        private static final GreenDaoManager INSTANCE = new GreenDaoManager();
    }

    /**
     * 对外唯一实例的接口
     *
     * @return
     */
    public static GreenDaoManager getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }

    /**
     * 初始化数据
     */
    private void init() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApplication.Companion.getContext(), AppConfig.TABLENAME);
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();

    }

    public DaoMaster getmDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getmDaoSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }


    public void clearAllTable() {
        Database database = mDaoMaster.getDatabase();
        DaoMaster.dropAllTables(database, true);
        DaoMaster.createAllTables(database, false);
    }


}
