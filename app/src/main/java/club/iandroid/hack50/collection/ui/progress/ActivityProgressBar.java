package club.iandroid.hack50.collection.ui.progress;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mydemo.R;

public class ActivityProgressBar extends Activity 
{
	private int[] data = new int[100];
	int hasData = 0;
	//记录ProgressBar的完成进度
	int status = 0;
	ProgressBar bar;

	ProgressDialog progDialog;

	//创建一个负责更新的进度的Handler
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			//表明消息是由该程序发送的
			if(msg.what == 0x11){
				bar.setProgress(status);
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_demo);
		bar = (ProgressBar)findViewById(R.id.bar);

		((Button)findViewById(R.id.btnProgressDialog)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//新建ProgressDialog对象
				progDialog = new ProgressDialog(ActivityProgressBar.this);
				//设置显示风格
				progDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				//设置标题
				progDialog.setTitle("慕课网");
				//设置图标
				progDialog.setIcon(R.drawable.youtube);

				/**
				 * 设置关于ProgressBar的一些属性
				 */
				//设定最大进度
				progDialog.setMax(100);
				//设置初始化已经增长到的进度
				progDialog.incrementProgressBy(50);
				//进度条是明确显示进度的
				progDialog.setIndeterminate(false);

				/**
				 * 设定一个按钮
				 *
				 */
				progDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(ActivityProgressBar.this, "good", Toast.LENGTH_LONG).show();
					}
				});

				//设置可以通过返回按钮退出退化框
				progDialog.setCancelable(true);

				//显示
				progDialog.show();
			}
		});

		//启动线程来执行任务
		new Thread()
		{
			public void run() {
				while(status<100){
					//获取耗时操作的完成百分比
					status = doWork();
					//发送消息
					handler.sendEmptyMessage(0x11);
				}
			};
		}.start();
	}
	
	//模拟一个耗时的操作
	public int doWork(){
		//为数组元素赋值
		data[hasData++] = (int)(Math.random() * 100);
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		return hasData;
	}
}
