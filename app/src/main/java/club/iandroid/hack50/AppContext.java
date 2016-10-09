package club.iandroid.hack50;

import android.app.Application;

/**
 * Created by Administrator on 2016/10/9.
 */

public class AppContext extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.init(this);
    }
}
