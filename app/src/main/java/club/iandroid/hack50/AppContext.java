package club.iandroid.hack50;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Administrator on 2016/10/9.
 */

public class AppContext extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.init(this);

        Realm.init(this);
    }
}
