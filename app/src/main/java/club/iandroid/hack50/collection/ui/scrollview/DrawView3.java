package club.iandroid.hack50.collection.ui.scrollview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by haoxi on 2016/1/22.
 */
public class DrawView3 extends View {

    /**
     * 记录每次触摸点的坐标
     */
    private int lastX;
    private int lastY;
    private int screenWidth;
    private int viewWidth;
    private int x2ScreenWidth;

    private Scroller mScroller;

    public DrawView3(Context context) {
        super(context);

        initView(context);
    }

    public DrawView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DrawView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setBackgroundColor(Color.YELLOW);
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        viewWidth = (int)(context.getResources().getDisplayMetrics().density*100+0.5);

        x2ScreenWidth = (screenWidth - viewWidth)/2;
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //每次获取坐标位置
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //每次按下获取最新的坐标位置
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;

                ((View) getParent()).scrollBy(-offsetX, -offsetY);
                break;
            case MotionEvent.ACTION_UP:
                //手指离开后，执行滑动过程
                View viewGroup = ((View) getParent());
                //如果滑动到中间屏幕则滑动回来，大于中间屏幕则滑动到对面
                Log.e("getScrollX", viewGroup.getScrollX() + "");
                Log.e("screenWidth", screenWidth + "");
                Log.e("viewWidth", viewWidth + "");
                Log.e("x2ScreenWidth", x2ScreenWidth + "");
                if (viewGroup.getScrollX() < -x2ScreenWidth) {
                    Log.e("ACTION_UP", "右侧");
                    mScroller.startScroll(
                            viewGroup.getScrollX(),
                            viewGroup.getScrollY(),
                            -(screenWidth - viewWidth + viewGroup.getScrollX()),
                            -viewGroup.getScrollY()
                    );
                } else {
                    Log.e("ACTION_UP", "左侧");
                    mScroller.startScroll(
                            viewGroup.getScrollX(),
                            viewGroup.getScrollY(),
                            -viewGroup.getScrollX(),
                            -viewGroup.getScrollY()
                    );
                }
                invalidate();
                break;
        }
        return true;
    }

    /**
     * Scroller通过 computeScrollOffset() 方法来判断是否完成了整个滑动
     * 同时提供了geCurrX()，getCurrY()方法来获得当前的滑动坐标。
     * <p/>
     * computeScroll()方法是不会自动调用的，只能通过invalidate()->draw()->computeScroll()来间接调用computeScroll()方法，
     * 所以需要在模板代码中调用invalidate()方法，实现循环获取scrollX和scrollY的目的。
     * 而当模拟过程结束后，scroller.computeScrollOffset()方法会返回false,从而中断循环，完成整个平滑移动过程
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        //判断Scroller是否执行完毕
        if (mScroller.computeScrollOffset()) {
            Log.e("computeScroll", mScroller.getCurrX() + "");
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //通过重绘来不断调用computeScroll
            invalidate();
        }
    }
}
