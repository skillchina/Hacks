package club.iandroid.hack50.dagger.component;

import club.iandroid.hack50.dagger.AppComponent;
import club.iandroid.hack50.dagger.module.LoginModule;
import club.iandroid.hack50.dagger.mvp.LoginActivity;
import club.iandroid.hack50.dagger.scope.ActivityScope;
import dagger.Component;

/**
 * Created by gabriel on 2017/4/6.
 */
@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {

    void inject(LoginActivity loginActivity);
}
