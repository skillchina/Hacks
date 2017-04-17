package club.iandroid.hack50.collection;


import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Time;
import android.widget.TextView;


public class ActivityLayout extends Activity
{
	private int currentColor = 0;
	//定义一个颜色数组
	final int[] colors = new int[]{R.color.black,R.color.gold,R.color.gray,R.color.red,R.color.white,R.color.yellow};
	
	final int[] names = new int[]{R.id.view01,R.id.view02,R.id.view03,R.id.view04,R.id.view05,R.id.view06};
	
	TextView[] views = new TextView[names.length];
	
	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(android.os.Message msg) {
			//表明消息来自本程序所发
			if(msg.what == 0x123)
			{
				for(int i = 0; i < names.length; i++)
				{
					views[i].setBackgroundResource(colors[(i+currentColor)%names.length]);
					
				}
				currentColor++;
			}
			super.handleMessage(msg);
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tablelayout_demo);
		for(int i=0;i<names.length;i++)
		{
			views[i] = (TextView)findViewById(names[i]);
		}
		//定义一个线程周期性地改变currentColor变量值
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				handler.sendEmptyMessage(0x123);
				
			}
		}, 0,200);
	}
}
