package club.iandroid.hack50.collection.ui.scrollview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.mydemo.R;

/**
 * 触摸事件
 * MotionEvent
 * 类型：
 *
 */
public class ActivityScrollview extends Activity implements View.OnClickListener {

    private TextView tv;
    private ScrollView scrollView;
    private Button btnUp;
    private Button btnDown;

    //单点触摸按下动作
    public static final int ACTION_DOWN = 0;
    //单点触摸离开动作
    public static final int ACTION_UP = 1;
    //触摸点移动动作
    public static final int ACTION_MOVE = 2;
    //触摸动作取消
    public static final int ACTION_CANCEL = 3;
    //触摸动作超出边界
    public static final int ACTION_OUTSIDE = 4;
    //多点触摸按下动作
    public static final int ACTION_POINTER_DOWN = 5;
    //多点离开动作
    public static final int ACTION_POINTER_UP = 6;

    /**
     * 通常情况下，我们会在onTouchEvent(MotionEvent event)方法中通过event.getAction()方法来获取触摸事件的类型，
     * 并使用switch-case方法来进行筛选，这个代码的模式基本固定
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_scrollview);

        btnUp = (Button)findViewById(R.id.up);
        btnDown = (Button)findViewById(R.id.down);
        btnUp.setOnClickListener(this);
        btnDown.setOnClickListener(this);

        tv = (TextView)findViewById(R.id.mtcontent);
        scrollView = (ScrollView)findViewById(R.id.mScrollview);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        /**
                         * 1、getScrollY()--滚动条滑动的距离
                         * 2、getMeasuredHeight()
                         * 3、getHeight()
                         */

                        //顶部状态
                        if(scrollView.getScrollY() <= 0){
                            Log.i("ScrollView","滚动到最顶部！");
                        }

                        //底部状态
                        //TextView的高度=一屏幕的高度+滚动条滚动距离
                        if(scrollView.getChildAt(0).getMeasuredHeight()<=scrollView.getHeight()
                                +scrollView.getScrollY()){
                            Log.i("ScrollView","滑动到底部");
                            Log.i("ScrollView","scrollView.getChildAt(0).getMeasuredHeight()="+scrollView.getChildAt(0).getMeasuredHeight()
                                    +"scrollView.getHeight()="+scrollView.getHeight()
                                    +"scrollView.getScrollY()="+scrollView.getScrollY());

                            tv.append(getResources().getString(R.string.text_scroll));
                        }
                        break;
                }
                return false;
            }
        });
    }

    //scrollTo:以滚动视图起始位置开始计算的
    //scrollBy:相对前一次的位置，去滚动对应的位置

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.up:
                scrollView.scrollBy(0, -30);
                break;
            case R.id.down:
                scrollView.scrollBy(0, 30);
                break;
        }
    }
}
