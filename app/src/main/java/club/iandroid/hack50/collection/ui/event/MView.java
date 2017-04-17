package club.iandroid.hack50.collection.ui.event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by haoxi on 2016/1/26.
 */
public class MView extends View {
    public MView(Context context) {
        super(context);
    }

    public MView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("MView 程序员吊了", "onTouchEventt "+event.getAction());
//        return super.onTouchEvent(event);
        return true;//已经处理完，不需要上级处理
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("MView 程序员吊了", "dispatchTouchEventt "+event.getAction());
        return super.dispatchTouchEvent(event);
    }
}
