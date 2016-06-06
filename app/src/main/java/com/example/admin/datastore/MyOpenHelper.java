package com.example.admin.datastore;

/**
 * Created by admin on 2016/6/5.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MyOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "edu"; // 数据库文件名称
    public static final String TABLE_NAME = "student"; // 表名
    public static final String ID = "studentId"; // 字段：ID
    public static final String PWD = "password"; // 字段：密码
    public static final String STUDENTNAME = "studentName"; //字段： 姓名
    public static final String GANDER = "sex"; //字段：性别
    public static final String CLASSNAME = "className"; //字段：班级
    // 调用父类构造器
    public MyOpenHelper(Context context, String name, CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
    }
    @Override	// 重写onCreate方法,首次执行时执行该方法，生成对应表
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists "
                + TABLE_NAME
                + " (" // 调用execSQL方法创建表
                + ID + " varchar primary key," + PWD + " varchar,"
                + STUDENTNAME + " varchar," + GANDER + " varchar," + CLASSNAME
                + " varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 重写onUpgrade方法
    }
}
