package com.example.studentassistant.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
* 1. 创建数据库类文件
* 2. 创建用户表
* 3. 定义对象
* 4. 绑定控件
* 5. 注册按钮功能的实现
* */

public class MyDBHelper extends SQLiteOpenHelper {

    // 定义两个常量
    private static final String DBNAME = "studentAssistant.db";
    private static final int VERSION = 1;

    // 初始化数据库
    public MyDBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    // 1. 创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建用户表
        db.execSQL("create table tb_userinfo(id integer primary key autoincrement, name varchar(10), pwd varchar(20), email varchar(50), phone varchar(11))");

        // 创建收入表
        db.execSQL("create table in_come(id integer primary key autoincrement, inMoney double, inTime varchar(20), inType varchar(30), inPayer varchar(30), inRemark varchar(30))");
        // 创建支出表
        db.execSQL("create table pay_out(id integer primary key autoincrement, outMoney double, outTime varchar(20), outType varchar(30), outPayee varchar(30), outRemark varchar(30))");
    }

    // 2. 升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        
    }
}
