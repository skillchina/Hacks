package club.iandroid.hack50;

import android.app.Application;

import club.iandroid.hack50.dagger.AppComponent;
import club.iandroid.hack50.dagger.DaggerAppComponent;
import club.iandroid.hack50.dagger.module.AppModule;
import io.realm.Realm;

/**
 * Created by Administrator on 2016/10/9.
 */

public class AppContext extends Application{

    private AppComponent appComponent;

    private static AppContext instance;

    public static AppContext getInstance() {
        if(instance == null){
            synchronized (AppContext.class){
                if(instance == null){
                    instance = new AppContext();
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.init(this);

        Realm.init(this);
        initComponent();

    }

    private void initComponent(){
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
