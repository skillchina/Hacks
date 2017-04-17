package club.iandroid.hack50.base;

import android.app.Activity;
import android.os.Bundle;

import club.iandroid.hack50.AppContext;
import club.iandroid.hack50.dagger.AppComponent;

/**
 * Created by gabriel on 2017/4/6.
 */

public abstract class BaseActivity extends Activity {

    private AppContext appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appContext = (AppContext)getApplication();

        setContentView(getContentView());

        setupActivityComponent(appContext.getAppComponent());

        initData();

    }

    protected abstract void setupActivityComponent(AppComponent appComponent);


    protected abstract int getContentView();

    protected abstract void initData();
}
