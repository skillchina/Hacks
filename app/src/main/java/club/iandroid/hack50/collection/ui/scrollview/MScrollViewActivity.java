package club.iandroid.hack50.collection.ui.scrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.mydemo.R;

/**
 * ScrollView 演示
 */
public class MScrollViewActivity extends AppCompatActivity {

    private ScrollView mScrollview;

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

    /**
     * View 获取坐标的以下方法
     *
     * getTop()：获取到的是View自身的顶边到其父布局顶边的距离
     * getLeft()：View自身的左边到其父布局左边的距离
     * getRight()：View自身的右边到其父布局的左边的距离
     * getBottom()： View自身的底边到其父布局顶边的距离
     *
     *
     * MotionEvent 提供的方法
     * getX()：获取点击事件距离控件左边的距离，即视图坐标
     * getY()：获取点击事件距离控件顶边的距离，即视图坐标
     *
     * getRawX()：获取点击事件距离整个屏幕左边的距离，即绝对坐标
     * getRawY()：获取点击事件距离整个屏幕顶端的距离，即绝对坐标
     *
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mscroll_view);

        mScrollview = (ScrollView)findViewById(R.id.mScrollview);

        addEvent();


    }

    private void addEvent(){
        mScrollview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    //单点触摸按下动作
                    case MotionEvent.ACTION_DOWN:

                        break;
                    //单点触摸离开动作
                    case MotionEvent.ACTION_UP:

                        break;
                    //触摸点移动动作
                    case MotionEvent.ACTION_MOVE:

                        break;
                    //触摸动作取消
                    case MotionEvent.ACTION_CANCEL:

                        break;
                    //触摸动作超出边界
                    case MotionEvent.ACTION_OUTSIDE:

                        break;
                    //多点触摸按下动作
                    case MotionEvent.ACTION_POINTER_DOWN:

                        break;
                    //多点离开动作
                    case MotionEvent.ACTION_POINTER_UP:

                        break;
                }
                return false;
            }
        });
    }


}
