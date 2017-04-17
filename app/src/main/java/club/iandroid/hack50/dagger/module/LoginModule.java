package club.iandroid.hack50.dagger.module;

import club.iandroid.hack50.dagger.mvp.LoginContract;
import club.iandroid.hack50.dagger.mvp.LoginPresenter;
import club.iandroid.hack50.dagger.scope.ActivityScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by gabriel on 2017/4/6.
 */
@Module
public class LoginModule {

    private LoginContract.View view;

    public LoginModule(LoginContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LoginContract.View proviceView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    LoginContract.Presenter providePresenter(LoginPresenter presenter){
        return presenter;
    }
}
