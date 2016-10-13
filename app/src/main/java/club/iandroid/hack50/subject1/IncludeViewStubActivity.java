package club.iandroid.hack50.subject1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import club.iandroid.hack50.R;

/**
 * Hack2 使用延迟加载以及避免代码重复
 * 使用<include/>标签避免代码重复
 * @author jiarong
 * @time 2016/10/13 14:12
 */
public class IncludeViewStubActivity extends AppCompatActivity {

    private ViewStub mViewStub;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_include_view_stub);
        mViewStub = (ViewStub)findViewById(R.id.map_stub);

        findViewById(R.id.btn_viewStub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(mViewStub.getVisibility()==View.GONE) {
//                    mViewStub.setVisibility(View.VISIBLE);
//                }else {
//                    mViewStub.setVisibility(View.GONE);
//                }

                if(mView == null) {
                    //只能使用一次
                    mView = mViewStub.inflate();
                }else {
                    if(mView.getVisibility()==View.VISIBLE) {
                        mView.setVisibility(View.GONE);
                    }else {
                        mView.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
}
