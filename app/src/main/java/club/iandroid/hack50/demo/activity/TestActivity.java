package club.iandroid.hack50.demo.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import club.iandroid.hack50.R;
import club.iandroid.hack50.utils.SharedPreferencesUtil;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        SharedPreferencesUtil sharedPreferencesUtil = SharedPreferencesUtil.getInstance();
        sharedPreferencesUtil.getSharedPreferences().getBoolean("key", true);
        sharedPreferencesUtil.getSharedPreferences().getFloat("key", 0);
        sharedPreferencesUtil.getSharedPreferences().getInt("key", 1);
        sharedPreferencesUtil.getSharedPreferences().getLong("key", 2);
        sharedPreferencesUtil.getSharedPreferences().getString("key", "test");

        sharedPreferencesUtil.edit().clear();//清空所有数据
        sharedPreferencesUtil.edit().putBoolean("test", false);//存入指定数据
        sharedPreferencesUtil.edit().remove("test");//删除指定数据
        sharedPreferencesUtil.edit().putBoolean("test", true).commit();//存入数据最后得提交，否则数据没办法保存

        /**
         * 1、访问其他程序的SharedPreferences的关键就是获取其他应用程序的Context
         * Context代表了访问该Android应用的全局信息的接口
         * 而android应用的包名正是该应用的唯一标识
         *
         */
        try {
            Context otherAppContext = createPackageContext("com.haoxitech.revenue", Context.CONTEXT_IGNORE_SECURITY);

            //2、使用Context获取对应的SharedPreferences
            SharedPreferences prefs = otherAppContext.getSharedPreferences("test", Context.MODE_WORLD_READABLE);

            //3.读取数据
            int count = prefs.getInt("count", 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}
