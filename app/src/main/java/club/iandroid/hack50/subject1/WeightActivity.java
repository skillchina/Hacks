package club.iandroid.hack50.subject1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import club.iandroid.hack50.LogUtils;
import club.iandroid.hack50.R;
import club.iandroid.hack50.Utils;

/**
 * Hack1 使用weight属性实现视图的居中显示
 *外部链接：https://developer.android.com/reference/android/widget/LinearLayout.html
 */
public class WeightActivity extends AppCompatActivity {

    private LinearLayout ll_parent;
    private TextView tv_child;
    private LinearLayout ll_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ll_parent = (LinearLayout) findViewById(R.id.ll_parent);
        tv_child = (TextView) findViewById(R.id.tv_child);
        ll_test = (LinearLayout) findViewById(R.id.ll_test);

        LogUtils.log("onCreate LinearLayout test width->getWidth:" + ll_test.getWidth() + "/getLayoutParams width" + ll_test.getLayoutParams().width
                + "\n height->getHeight:" + ll_test.getHeight()+ "/getLayoutParams height" + ll_test.getLayoutParams().height
        );

        int[] widthHeight = Utils.getWidthAndHeight(ll_test);
        int parentWidth = widthHeight[0];
        int parentHeight = widthHeight[1];
        LogUtils.log("parentWidth:" + parentWidth
                + "\n parentHeight:" + parentHeight
                + " child's width:"
                + (0 + 0.5 * parentWidth / 1));

        findViewById(R.id.btn_get_width).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hack1();
            }
        });
    }

    private void hack1() {
        //一半的TextView的宽度计算
        //TextView's width + TextView's weight * parentView's width / sum(weight)

        LinearLayout ll_test = (LinearLayout) findViewById(R.id.ll_test);
        //在组件外部获取宽高，不能直接使用getWidth,getHeight--》值都是0
        int[] widthHeight = Utils.getWidthAndHeight(ll_test);
        int parentWidth = widthHeight[0];
        int parentHeight = widthHeight[1];
        //当组件设置fill_parent,组件中包含其他子组件时，所获得的实际值是这些组件所占的最小宽度和最小高度
        //public static final int MATCH_PARENT = -1;
        //public static final int WRAP_CONTENT = -2;
        //public static final int FILL_PARENT = -1;

        LogUtils.log("LinearLayout test width->getWidth:" + ll_test.getWidth() + "\n height->getHeight:" + ll_test.getHeight());

        LogUtils.log("parentWidth:" + parentWidth + "/getLayoutParams width" + ll_test.getLayoutParams().width
                + "\n parentHeight:" + parentHeight + "/getLayoutParams height" + ll_test.getLayoutParams().height
                + " child's width:"
                + (0 + 0.5 * parentWidth / 1));

        int[] tvWidthHeight = Utils.getWidthAndHeight(tv_child);
        int childWidth = tvWidthHeight[0];
        int childHeight = tvWidthHeight[1];
        LogUtils.log("childWidth:" + childWidth + "/getLayoutParams width" + tv_child.getLayoutParams().width
                + " \nchildHeight:" + childHeight + "/getLayoutParams height" + tv_child.getLayoutParams().height);

        TextView tv_text = (TextView) findViewById(R.id.tv_text);
        int[] textWidthHeight = Utils.getWidthAndHeight(tv_text);
        int textWidth = textWidthHeight[0];
        int textHeight = textWidthHeight[1];
        LogUtils.log("textWidth:" + textWidth + "/getLayoutParams width" + tv_text.getLayoutParams().width
                + " \ntextHeight:" + textHeight + "/getLayoutParams height" + tv_text.getLayoutParams().height);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            LogUtils.log("onWindowFocusChanged true LinearLayout test width->getWidth:" + ll_test.getWidth() + "\n height->getHeight:" + ll_test.getHeight());
        }else {
//            LogUtils.log("onWindowFocusChanged false LinearLayout test width->getWidth:" + ll_test.getWidth() + "\n height->getHeight:" + ll_test.getHeight());
        }
    }
}
