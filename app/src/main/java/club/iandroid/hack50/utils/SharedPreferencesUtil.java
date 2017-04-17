package club.iandroid.hack50.utils;

import android.content.Context;
import android.content.SharedPreferences;

import club.iandroid.hack50.AppContext;

/**
 * Created by gabriel on 2017/4/10.
 */

public class SharedPreferencesUtil {

    private static SharedPreferencesUtil instance;

    private SharedPreferences sharedPreferences;

    public static SharedPreferencesUtil getInstance() {
        if(instance == null){
            synchronized (SharedPreferencesUtil.class){
                if(instance == null){
                    instance = new SharedPreferencesUtil();
                    instance.init();
                }
            }
        }
        return instance;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    private void init(){
        /**
         * Context.MODE_PRIVATE 指定数据只能被本应用程序读写
         *
         * Context.MODE_WORLD_READABLE 指定数据能被其他应用程序读，但不能写
         * As of {@link android.os.Build.VERSION_CODES#N} attempting to use this
         * mode will throw a {@link SecurityException}.
         *
         * Context.MODE_WORLD_WRITEABLE 指定数据能被其他应用程序读、写
         */

        sharedPreferences = AppContext.getInstance().getSharedPreferences("test", Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor edit(){
        return sharedPreferences.edit();
    }
}
