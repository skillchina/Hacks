package club.iandroid.hack50.collection.ui.viewflipper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mydemo.R;

public class ActivityAdapterViewFlipper extends Activity
{
	//定义一个访问图片的数组
	int[] images = new int[]{R.drawable.block_1,R.drawable.block_2,
				R.drawable.block_3,R.drawable.block_4,R.drawable.block_5};
		
	AdapterViewFlipper flipper;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adapterviewflipper_demo);
		context = this;
		
		flipper = (AdapterViewFlipper)findViewById(R.id.flipper);
		//创建一个BaseAdapter对象，该对象负责提供所显示的列表项
		BaseAdapter adapter = new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				//创建一个ImageView
				ImageView img = new ImageView(context);
				img.setImageResource(images[position]);
				//设置ImageView的缩放类型
				img.setScaleType(ImageView.ScaleType.FIT_XY);
				//为ImageView设置布局参数
				img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
				return img;
			}
			
			@Override
			public long getItemId(int position) {
				
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return images[position];
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return images.length;
			}
		};
		
		flipper.setAdapter(adapter);
		flipper.setFlipInterval(1000);
		
		Button pre = (Button)findViewById(R.id.pre);
		pre.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//显示上一个组件
				flipper.showPrevious();
				//停止播放
				flipper.stopFlipping();
			}
		});
		Button next = (Button)findViewById(R.id.next);
		next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//显示下一个组件
				flipper.showNext();
				//停止播放
				flipper.stopFlipping();
			}
		});
		Button auto = (Button)findViewById(R.id.auto);
		auto.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//开始自动播放
				flipper.startFlipping();
			}
		});
	}
}
