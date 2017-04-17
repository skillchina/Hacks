package club.iandroid.hack50.dagger;

import javax.inject.Singleton;

import club.iandroid.hack50.AppContext;
import club.iandroid.hack50.dagger.module.AppModule;
import dagger.Component;

/**
 * Created by gabriel on 2017/4/6.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    AppContext application();


}
