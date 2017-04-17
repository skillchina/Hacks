package club.iandroid.hack50.collection.ui.event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by haoxi on 2016/1/26.
 */
public class ViewGroupB extends LinearLayout {
    public ViewGroupB(Context context) {
        super(context);
    }

    public ViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("ViewGroupB 部长来找茬", "dispatchTouchEvent "+event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("ViewGroupB 部长来找茬", "onInterceptTouchEvent "+ev.getAction());
        return super.onInterceptTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("ViewGroupB 部长来找茬","onTouchEvent "+event.getAction());
        return super.onTouchEvent(event);
//        return true;
    }
}
