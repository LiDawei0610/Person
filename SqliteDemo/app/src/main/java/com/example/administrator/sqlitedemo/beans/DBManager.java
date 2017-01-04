package com.example.administrator.sqlitedemo.beans;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
public class DBManager {
    private final static String dbName = "test_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;
    public DBManager(Context context){
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context,dbName,null);
    }
    /**
     * 获取单例引用
     */
    public static  DBManager getInstance(Context context){
        if(mInstance==null){
            synchronized (DBManager.class){
                if(mInstance==null){
                    mInstance=new DBManager(context);
                }
            }
        }
        return mInstance;
    }
    /**
     * 获取可读数据库
     */
    private SQLiteDatabase  getReadableDatabase(){
        if(openHelper==null){
            openHelper = new DaoMaster.DevOpenHelper(context,dbName,null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }
    /**
     * 获取可写数据库
     */
    private SQLiteDatabase  getWritableDatabase(){
        if(openHelper==null){
            openHelper = new DaoMaster.DevOpenHelper(context,dbName,null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }
    /**
     * 插入一条记录
     *
     */
    public void insertUser(UserBean userBean){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
         UserBeanDao userBeanDao = daoSession.getUserBeanDao();
        userBeanDao.insert(userBean);
    }
    /**
     * 插入用户集合
     */
    public void insertUserBeanList(List<UserBean> userBeans){
        if(userBeans==null||userBeans.isEmpty()){
            return;
        }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserBeanDao userBeanDao = daoSession.getUserBeanDao();
        userBeanDao.insertInTx(userBeans);
    }
    /**
     * 删除一条数据
     */
    public void deleteUser(UserBean userBean){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserBeanDao userBeanDao = daoSession.getUserBeanDao();
        userBeanDao.delete(userBean);
    }
    /**
     * 更新一条数据
     */
    public void update(UserBean userBean){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserBeanDao userBeanDao = daoSession.getUserBeanDao();
        userBeanDao.update(userBean);
    }
    /**
     * 查询用户列表
     */
    public List<UserBean> queryUserBeanList(){
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserBeanDao userBeanDao = daoSession.getUserBeanDao();
        QueryBuilder<UserBean> qb = userBeanDao.queryBuilder();
         List<UserBean> list = qb.list();
        return list;
    }
    /**
     * 查询用户列表
     */
    public List<UserBean> queryUserBeanList(int age){
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserBeanDao userBeanDao = daoSession.getUserBeanDao();
        QueryBuilder<UserBean> qb = userBeanDao.queryBuilder();
        qb.where(UserBeanDao.Properties.Age.gt(age)).orderAsc(UserBeanDao.Properties.Age);
        List<UserBean> list = qb.list();
        return list;
    }

}

