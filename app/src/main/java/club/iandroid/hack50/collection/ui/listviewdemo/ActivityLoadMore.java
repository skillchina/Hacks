package club.iandroid.hack50.collection.ui.listviewdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import com.example.mydemo.R;

public class ActivityLoadMore extends ListActivity implements AbsListView.OnScrollListener
{

    
    static List<String> list = new ArrayList<String>();
	private ViewSwitcher switcher;
	Context context;
	int count = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		context = this;
		for(int i=0;i<10;i++){
			list.add("List Item "+i);
		}
		switcher = new ViewSwitcher(this);
		Button btnLoadMore = (Button)View.inflate(this, R.layout.loadmore_demo, null);
		btnLoadMore.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				switcher.showNext();
				new getMoreItems().execute();

			}
		});
		View progress = (View)View.inflate(this, R.layout.loadmoreview_demo, null);
		
		switcher.addView(btnLoadMore);
		switcher.addView(progress);

		ListView listView = getListView();
		listView.addFooterView(switcher);

		listView.setOnScrollListener(this);
		
		setListAdapter(new ArrayAdapter(context, android.R.layout.simple_list_item_1, list));
	}
	
	private class getMoreItems extends AsyncTask{

		@Override
		protected Object doInBackground(Object... params) {
			try{
				count++;
				for(int i=count*10;i<count*10+10;i++){
					list.add("List Item "+i);
				}
				Thread.sleep(3000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Object result) {
			setListAdapter(new ArrayAdapter(context,android.R.layout.simple_list_item_1,list));
			switcher.showPrevious();
		}
	}

	/////滚动事件

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		switch (scrollState){
			case SCROLL_STATE_FLING:
				Log.i("ListView","用户在手指离开屏幕之前，由于用力滑了一下，视图仍依靠惯性继续滑动。");
				switcher.showNext();
				new getMoreItems().execute();
				break;
			case SCROLL_STATE_IDLE:
				Log.i("ListView","视图已经停止滑动");
				break;
			case SCROLL_STATE_TOUCH_SCROLL:
				Log.i("ListView","手指没有离开屏幕，视图正在滑动");
				break;
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

	}
}
