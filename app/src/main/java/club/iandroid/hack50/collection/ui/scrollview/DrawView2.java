package club.iandroid.hack50.collection.ui.scrollview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by haoxi on 2016/1/22.
 */
public class DrawView2 extends View {

    /**
     * 记录每次触摸点的坐标
     */
    private int lastX;
    private int lastY;

    public DrawView2(Context context) {
        super(context);
        initView();
    }

    public DrawView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DrawView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        setBackgroundColor(Color.YELLOW);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //每次获取坐标位置
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //每次按下获取最新的坐标位置
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;

                ((View)getParent()).scrollBy(-offsetX, -offsetY);
                break;
        }
        return true;
    }
}
