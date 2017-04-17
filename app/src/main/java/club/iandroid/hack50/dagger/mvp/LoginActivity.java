package club.iandroid.hack50.dagger.mvp;


import club.iandroid.hack50.R;
import club.iandroid.hack50.base.BaseActivity;
import club.iandroid.hack50.dagger.AppComponent;
import club.iandroid.hack50.dagger.component.DaggerLoginComponent;

public class LoginActivity extends BaseActivity {


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

    }
}
