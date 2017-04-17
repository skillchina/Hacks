package club.iandroid.hack50.collection.ui.listviewdemo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.mydemo.R;
import com.example.mydemo.ui.widget.MListView;

import java.util.ArrayList;
import java.util.List;

/**
 * ListViewTest
 */
public class ListViewActivity extends AppCompatActivity {

    private MListView mListView;
    private Button btn_data;
    private Button btn_nodata;
    private Button btn_listviewhideorshow;
    private View progress;
    private Context context;
    private int count = 0;
    private List<String> datas = new ArrayList<String>();
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        context = this;
        initView();

        setOnTouchListener();
        setOnScrollListener();
    }

    /**
     * 初始化界面
     */
    private void initView(){

        btn_listviewhideorshow = (Button)findViewById(R.id.btn_listviewhideorshow);
        btn_listviewhideorshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListViewActivity.this, ListViewWithTopShowOrHide.class));
            }
        });

        btn_data = (Button)findViewById(R.id.btn_data);
        btn_nodata = (Button)findViewById(R.id.btn_nodata);
        btn_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initData();
            }
        });
        btn_nodata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datas.clear();
                adapter.notifyDataSetChanged();
            }
        });
        mListView = (MListView)findViewById(R.id.mListView);
        mListView.setEmptyView(findViewById(R.id.empty_view));
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, datas);
        mListView.setAdapter(adapter);

        progress = (View)View.inflate(this, R.layout.loadmoreview_demo, null);
        progress.setVisibility(View.GONE);
        //增加加载更多按钮
        mListView.addFooterView(progress);


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

    /**
     * 设置事件
     */
    private void setOnTouchListener(){
        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //触摸时操作
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //移动时操作
                        break;
                    case MotionEvent.ACTION_UP:
                        //离开时操作
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 该成员变量 用来记录上次第一个可视的Item的ID 并与当前的可视Item的ID进行比较，
     * 即可知道当前滚动的方向
     */
    private int lastVisibleItemPosition;

    /**
     * 设置滚动事件
     */
    private void setOnScrollListener(){
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_IDLE:
                        //滑动停止时
                        Log.d("Test", "SCROLL_STATE_IDLE");
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        //正在滚动
                        Log.d("Test", "SCROLL_STATE_TOUCH_SCROLL");
                        break;
                    case SCROLL_STATE_FLING:
                        //手指抛动时，即手指用力滑动
                        //在离开后ListView由于惯性继续滑动

                        /**
                         * 当用户没有做手指抛动的状态时，这个方法只会回调2次，否则会回调3次，差别就是手指抛动的这个状态。
                         * 通常情况下，我们会在这个方法中通过不同的状态来设置一些标志Flag，来区分不同的滑动状态，供其他方法处理。
                         */
                        Log.d("Test", "SCROLL_STATE_FLING");
                        break;
                }
            }

            /**
             *
             * @param absListView
             * @param firstVisibleItem 当前能看见的第一个Item的ID(从0开始)
             * @param visibleItemCount 当前能看见的Item总数
             *                         这里需要注意的是，当前能看见的Item数，包括没有显示完整的Item，即显示一小半的Item也包括在内了。
             *
             * @param totolItemCount 整个ListViewI 的item总数
             */
            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totolItemCount) {
                //滚动时一直调用

                /**
                 * 判断当前是否滚动到最后一行
                 */
                if (firstVisibleItem + visibleItemCount == totolItemCount
                        && totolItemCount > 0) {
                    //滚动到最后一行
                    Log.d("Test", "滚动到最后一行");
                    progress.setVisibility(View.VISIBLE);
                    new getMoreItems().execute();
                }

                /**
                 * 判断滚动的方向
                 */
                if (firstVisibleItem > lastVisibleItemPosition) {
                    //上滑
//                    Log.d("Test", "上滑");
                } else if (firstVisibleItem < lastVisibleItemPosition) {
                    //下滑
//                    Log.d("Test", "下滑");
                }
                lastVisibleItemPosition = firstVisibleItem;

                Log.d("Test", "onScroll");
            }
        });
    }

    private class getMoreItems extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object... params) {
            try{
                count++;
                for(int i=count*10;i<count*10+10;i++){
                    datas.add("New Item " + i);
                }
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, datas);
            mListView.setAdapter(adapter);
            progress.setVisibility(View.GONE);
        }
    }
}
