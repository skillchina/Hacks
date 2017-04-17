package club.iandroid.hack50.collection.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jiaronggeng on 15-pic5-19.
 * SQLiteOpenHelper是Android提供的一个管理数据库的工具类，可用于管理数据库的创建和版本更新。
 * 一般的用法是创建SQLiteOpenHelper的子类，
 * 并扩展它的onCreate(SQLiteDatabase db)和onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)方法
 * 常用的方法：
 * pic1、synchronized SQLiteDatabase getReadableDatabase():创建或打开一个 只读数据库
 *
 *
 * pic2、synchronized SQLiteDatabase getWritableDatabase():创建或打开一个 读写数据库
 *
 *
 * pic3、abstract void onCreate(SQLiteDatabase db):当第一次创建数据库时回调该方法
 * 用于初次使用软件时生成数据库表，当调用SQLiteOpenHelper的getWritableDatabase或者getReadableDatabase()方法
 * 获取用于操作数据库的SQLiteDatabase实例时，如果数据库不存在，Android系统会自动生成一个数据库，接着调用onCreate（）方法，
 * onCreate()方法在初次生成数据库时才会被调用，重写onCreate()方法时，可以生成数据库表结构及添加一些应用使用到的初始化数据。
 *
 *
 * pic4、abstract void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion):
 * 当数据库版本更新时回调该方法
 * 实际上，当应用程序升级表结构时，完全可能因为已有的数据导致升级失败。
 * 在这种时候程序可能需要先对数据进行转存，清空数据表中的记录，接着对数据表进行更新，当数据表更新完成后再降数据保存回来。
 *
 *
 * pic5、synchronized void close():关闭所有打开的SQLiteDatabase
 */
public class DataBaseHelper extends SQLiteOpenHelper
{

    final String CREATE_TABLE_SQL_USER = "create table if not exists user(_id integer primary key autoincrement," +
            "name text, age integer)";
    public DataBaseHelper(Context context, String name, int version)
    {
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * 这个方法
         * pic1、在第一次打开数据库的时候才会走
         * pic2、在清除数据之后再次运行->打开数据库，这个方法会走
         * pic3、没有清除数据，不会走这个方法
         * pic4、数据库升级的时候这个方法不会走
         */

        //第一次使用数据库时自动建表
        db.execSQL(CREATE_TABLE_SQL_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /**
         * pic1、第一次创建数据库的时候，这个方法不会走
         * pic2、清除数据后再次运行(相当于第一次创建)这个方法不会走
         * pic3、数据库已经存在，而且版本升高的时候，这个方法才会调用
         */

        String sql = "alter table user add sex";
        db.execSQL(sql);
        System.out.print("-------onUpdate Called-------"+oldVersion+"----->"+newVersion);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /**
         * 执行数据库的降级操作
         * pic1、只有新版本比旧版本低的时候才会执行
         * pic2、如果不执行降级操作，会抛出异常
         */
        System.out.print("-------onDowngrade Called-------"+oldVersion+"----->"+newVersion);
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
