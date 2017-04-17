package club.iandroid.hack50.dagger.mvp;

/**
 * Created by gabriel on 2017/4/6.
 */

public interface LoginContract {

    interface View{
        void loginSuccess();
    }

    interface Presenter{
        void doLogin();
    }
}
