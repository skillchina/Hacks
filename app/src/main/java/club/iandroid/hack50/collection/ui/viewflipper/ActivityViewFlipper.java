package club.iandroid.hack50.collection.ui.viewflipper;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.mydemo.R;


public class ActivityViewFlipper extends Activity {

    ViewFlipper viewFlipper;
    int[] imageViews = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
            R.drawable.pic4, R.drawable.pic5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_view_flipper);

        viewFlipper = (ViewFlipper)findViewById(R.id.mviewflipper);

        //动态导入的方式为ViewFlipper加入子View
        for(int i=0;i<imageViews.length;i++){
            viewFlipper.addView(getImageview(imageViews[i]));
        }
        //为ViewFlipper添加动画效果
        /*
        viewFlipper.setInAnimation(this, R.anim.slide_in_right);
        viewFlipper.setOutAnimation(this, R.anim.slide_out_left);

        //设定切换的时间间隔
        viewFlipper.setFlipInterval(3000);
        //开始播放
        viewFlipper.startFlipping();*/
    }

    private ImageView getImageview(int resId){
        ImageView img = new ImageView(this);
        //img.setImageResource(resId);
        img.setBackgroundResource(resId);
        return img;
    }

    double startX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                //向右滑动
                if(event.getX()-startX>50){
                    viewFlipper.setInAnimation(this, R.anim.slide_in_left);
                    viewFlipper.setOutAnimation(this, R.anim.slide_out_left);
                    viewFlipper.showNext();//显示前一页
                }
                //向左滑动
                if(startX-event.getX()>50){
                    viewFlipper.setInAnimation(this, R.anim.slide_in_right);
                    viewFlipper.setOutAnimation(this, R.anim.slide_out_right);
                    viewFlipper.showPrevious();//显示后一页
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }
}
