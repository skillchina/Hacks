package club.iandroid.hack50.collection.ui.scrollview;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 侧边滑动
 * Created by haoxi on 2016/1/26.
 */
public class DrawView4 extends FrameLayout {

    private ViewDragHelper mViewDrawHelper;
    private View mMenuView, mMainView;
    private int mWidth;

    public DrawView4(Context context) {
        super(context);
        initView();
    }

    public DrawView4(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DrawView4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mMenuView.getMeasuredWidth();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将触摸事件传递给ViewDrawHelper，此操作必不可少
        mViewDrawHelper.processTouchEvent(event);
        return true;

    }

    private void initView(){
        mViewDrawHelper = ViewDragHelper.create(this, callBack);
    }

    private ViewDragHelper.Callback callBack = new ViewDragHelper.Callback() {
        //何时开始检测触摸事件
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            //如果档期触摸的child是mMainView时开始检测
            return mMainView == child;
        }

        //处理垂直滑动
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        //处理水平滑动

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        //拖动结束后调用
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            //手指抬起后缓慢移动到指定位置
            if(mMainView.getLeft() < 300){
                //关闭菜单
                //相当于Scroller的startScroll方法
                mViewDrawHelper.smoothSlideViewTo(mMainView, 0 ,0);
                ViewCompat.postInvalidateOnAnimation(DrawView4.this);
            }else {
                //打开菜单
                mViewDrawHelper.smoothSlideViewTo(mMainView, 300, 0);
                ViewCompat.postInvalidateOnAnimation(DrawView4.this);
            }
        }
    };

    @Override
    public void computeScroll() {
        if(mViewDrawHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
