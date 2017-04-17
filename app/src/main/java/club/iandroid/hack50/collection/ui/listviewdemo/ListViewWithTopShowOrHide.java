package club.iandroid.hack50.collection.ui.listviewdemo;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;

import com.example.mydemo.R;
import com.example.mydemo.ui.widget.MListView;

import java.util.ArrayList;
import java.util.List;

/**
 * ListView 滑动 头部隐藏 和 显示的演示
 */
public class ListViewWithTopShowOrHide extends Activity{

    private MListView mListView;
    private float mFirstY;
    private float mCurrentY;
    private float mTouchSlop;
    private int direction;//标识方向
    private boolean mShow = true;
    private List<String> datas = new ArrayList<String>();
    private ArrayAdapter adapter;
    private Context context;
    private ObjectAnimator animator;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_with_top_show_or_hide);
        context = this;

        mToolbar = (Toolbar)findViewById(R.id.mToolbar);

        mListView = (MListView)findViewById(R.id.mListView);

        //添加头部
        View header = new View(this);
        header.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                (int)getResources().getDimension(R.dimen.abc_action_bar_default_height_material)));

        mListView.addHeaderView(header);
        mListView.setOnTouchListener(myTouchListener);

        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, datas);
        mListView.setAdapter(adapter);

        /**
         * 获取系统认为的最低滑动距离
         */
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();

        initData();
    }

    /**
     * 加载数据
     */
    private void initData(){
        for(int i=0;i<20;i++){
            datas.add("item"+i);
        }
        adapter.notifyDataSetChanged();

    }

    private View.OnTouchListener myTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //触摸时操作
                    mFirstY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mCurrentY = event.getY();
                    if(mCurrentY - mFirstY > mTouchSlop){
                        direction = 0;//down
                    }else if(mFirstY - mCurrentY > mTouchSlop){
                        direction = 1;//up
                    }
                    if(direction == 1){//向上
                        Log.d("mShow", "direction: 向上滑动"+direction+" mShow:"+mShow);
                        if(mShow){
                            mShow = !mShow;
                            toolbarAnim(mShow);//隐藏
                        }
                    }else if(direction == 0){//向下
                        Log.d("mShow", "direction:向下滑动"+direction+" mShow:"+mShow);
                        if(!mShow){
                            mShow = !mShow;
                            toolbarAnim(mShow);//显示
                        }
                    }
                    //移动时操作
                    break;
                case MotionEvent.ACTION_UP:
                    //离开时操作
                    break;
            }
            return false;
        }
    };

    /**
     * 是否显示toolbar
     * @param isshow
     */
    private void toolbarAnim(boolean isshow){
        if(animator!=null && animator.isRunning()){
            animator.cancel();
        }

        if(isshow){
            //显示
            animator = ObjectAnimator.ofFloat(mToolbar, "translationY", mToolbar.getTranslationY(), 0);
        }else {
            //隐藏
            animator = ObjectAnimator.ofFloat(mToolbar, "translationY", mToolbar.getTranslationY(), -mToolbar.getHeight());
        }
        animator.start();

    }
}
