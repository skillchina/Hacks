package club.iandroid.hack50.collection.ui.viewswitch;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import android.widget.ViewSwitcher.ViewFactory;

import com.example.mydemo.R;

public class ActivityViewSwitcher extends Activity 
{
	//定义一个常量，用于显示每屏显示的应用程序数
	public static final int NUMBER_PRE_SCREEN = 12;
	//代表应用程序的内部类
	public static class DataItem
	{
		//应用程序名称
		public String dataName;
		//应用程序图标
		public Drawable drawable;
	}
	//保存系统所有应用程序的List集合
	private ArrayList<DataItem> items = new ArrayList<DataItem>();
	
	//记录当前正在显示第几屏的程序
	private int screenNo = -1;
	//保存程序所占的总屏幕数
	private int screenCount;
	ViewSwitcher switcher;
	//创建LayOutInflater对象
	LayoutInflater inflater;
	
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewswitcher_demo);
		context = this;
		inflater = LayoutInflater.from(context);
		//创建一个包含40个元素的List集合，用于模拟包含40个应用程序
		for(int i=0;i<40;i++){
			String label = ""+i;
			Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);
			DataItem item = new DataItem();
			item.dataName = label;
			item.drawable = drawable;
			items.add(item);
		}
		//计算应用程序所占的总屏数
		//如果应用程序的数量能整除NUMBER_PRE_SCREEN,除法的结果就是总屏数
		//如果不能整除，总屏数应该是除法的结果加1
		screenCount = items.size()%NUMBER_PRE_SCREEN==0?
					items.size()/NUMBER_PRE_SCREEN:
					items.size()/NUMBER_PRE_SCREEN+1;
		switcher = (ViewSwitcher)findViewById(R.id.viewSwitcher);
		switcher.setFactory(new ViewFactory() {
			
			//实际上就是返回一个GridView组件
			@Override
			public View makeView() {
				//加载R.layout.slidelistview组件，实际上就是一个GridView组件
				return inflater.inflate(R.layout.slidelistview, null);
			}
		});
		//页面加载时先显示第一屏
		next(null);
		
		Button btnPre = (Button)findViewById(R.id.btn_pre);
		Button btnNext = (Button)findViewById(R.id.btn_next);
		btnPre.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pre(v);
			}
		});
		
		btnNext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				next(v);
			}
		});
	}
	
	public void next(View v)
	{
		if(screenNo<screenCount-1){
			screenNo++;
			//为ViewSwitcher的组件显示过程设置动画
			switcher.setInAnimation(this, R.anim.slide_in_right);
			//为ViewSwitcher的组件隐藏过程设置动画
			switcher.setOutAnimation(this, R.anim.slide_out_left);
			//控制下一屏将要显示的GridView对应的Adapter
			((GridView)switcher.getNextView()).setAdapter(adapter);
			//单击右键按钮，显示下一屏
			switcher.showNext();
		}
	}
	
	public void pre(View v)
	{
		if(screenNo>0){
			screenNo--;
			//为ViewSwitcher的组件显示过程设置动画
			switcher.setInAnimation(this, R.anim.slide_out_left);
			//为ViewSwitcher的组件隐藏过程设置动画
			switcher.setOutAnimation(this, R.anim.slide_in_right);
			//控制下一屏将要显示的GridView对应的Adapter
			((GridView)switcher.getNextView()).setAdapter(adapter);
			//单击右键按钮，显示下一屏
			switcher.showPrevious();
		}
	}
	
	//该BaseAdapter负责为每屏显示的GridView提供列表项
	private BaseAdapter adapter = new BaseAdapter() {
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if(convertView==null){
				//加载R.layout.labelicon布局文件
				view = inflater.inflate(R.layout.labelicon, null);
			}
			ImageView img = (ImageView)view.findViewById(R.id.img);
			TextView tv = (TextView)view.findViewById(R.id.title);
			DataItem item = (DataItem)getItem(position);
			img.setImageDrawable(item.drawable);
			tv.setText(item.dataName);
			return view;
		}
		
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		
		@Override
		public Object getItem(int position) {
			return items.get(screenNo*NUMBER_PRE_SCREEN+position);
		}
		
		@Override
		public int getCount() {
			//如果已经到了最后一屏，且应用程序的数量不能整除NUMBER_PRE_SCREEN
			if(screenNo==screenCount-1 && items.size()%NUMBER_PRE_SCREEN!=0){
				//最后一屏显示的程序数为应用程序的数量对NUMBER_PRE_SCREEN求余
				return items.size()%NUMBER_PRE_SCREEN;
			}
			return NUMBER_PRE_SCREEN;
		}
	};
}
