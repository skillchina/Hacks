package club.iandroid.hack50.dagger.module;

import javax.inject.Singleton;

import club.iandroid.hack50.AppContext;
import dagger.Module;
import dagger.Provides;

/**
 * Created by gabriel on 2017/4/6.
 */
@Module
public class AppModule {

    private AppContext appContext;

    public AppModule(AppContext appContext) {
        this.appContext = appContext;
    }

    @Singleton
    @Provides
    public AppContext provideApplication(){
        return appContext;
    }

}
