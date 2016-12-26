package com.example.administrator.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**自定义创建数据库的帮助类：创建数据库和更新数据库，打开数据库
 * Created by Administrator on 2016/12/26.
 */
public class MyOpenHelper  extends SQLiteOpenHelper {
    private String sql="create table user(_id integer primary key autoincrement,"+
            "name text,password text)";
    //private String sql1="";
    //private String sql2="";
    public MyOpenHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory,
                        int version){
        super(context,name,factory,version);
    }
    @Override
    //创建数据库表，只会调用一次
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       sqLiteDatabase.execSQL(sql);
        //sqLiteDatabase.execSQL(sql1);
    }

    @Override
    //更新数据库表的回调函数 --第二个参数和第三个参数是新旧版本号
    //新旧版本号不一样是，被触发
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       //添加表
        //更新表
    }
}
