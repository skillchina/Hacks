package club.iandroid.hack50.collection;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.mydemo.db.SQLiteActivity;
import com.example.mydemo.ui.GridView.ActivityGridView;
import com.example.mydemo.ui.GridView.ActivityGridView2;
import com.example.mydemo.ui.StackViewActivity;
import com.example.mydemo.ui.actionbar.ActionBarActivity;
import com.example.mydemo.ui.asynctask.AsyncTaskActivity;
import com.example.mydemo.ui.asynctask.MyAsyncTaskActivity;
import com.example.mydemo.ui.button.ActivityButton;
import com.example.mydemo.ui.calendar.ActivityCalendar;
import com.example.mydemo.ui.calendar.ActivityClock;
import com.example.mydemo.data.SharedPreferenceActivity;
import com.example.mydemo.ui.edittext.ActivityComplete;
import com.example.mydemo.file.FileReadWriteActivity;
<<<<<<< HEAD
import com.example.mydemo.ui.gallery.ActivityGallery;
import com.example.mydemo.ui.imageview.ActivityImageView;
import com.example.mydemo.ui.listviewdemo.ActivityExpandableListView;
import com.example.mydemo.ui.listviewdemo.ActivityLoadMore;
import com.example.mydemo.ui.listviewdemo.ActivityMyListView;
import com.example.mydemo.ui.listviewdemo.ListViewActivity;
import com.example.mydemo.ui.mediaplayer.MediaPlayerActivity;
import com.example.mydemo.ui.mfragment.MFragemntActivity;
import com.example.mydemo.ui.progress.ActivityProgressBar;
import com.example.mydemo.ui.scrollview.ActivityScrollview;
import com.example.mydemo.ui.spinner.ActivitySpinner;
import com.example.mydemo.ui.tab.TabbedActivity;
import com.example.mydemo.ui.textview.MarqueeActivity;
import com.example.mydemo.ui.viewflipper.ActivityAdapterViewFlipper;
import com.example.mydemo.ui.viewflipper.ActivityViewFlipper;
import com.example.mydemo.ui.viewpager.ActivityViewPager;
import com.example.mydemo.ui.viewpager.AutoScrollViewPagerActivity;
import com.example.mydemo.ui.viewswitch.ActivityViewSwitcher;
import com.example.mydemo.volley.VolleyActivity;
import com.example.mydemo.ui.webview.ActivityWebView;
=======
import com.example.mydemo.listviewdemo.ListViewActivity;
import com.example.mydemo.mfragment.MFragemntActivity;
>>>>>>> origin/master

public class ActivityMain extends Activity
{
	private Context context;
	static final int NOTIFICATION_ID = 0x123;
	NotificationManager nm;
	
	//为发送通知的按钮点击事件自定义事件处理方法
	@SuppressLint("NewApi")
	public void send(View source)
	{
		//创建一个启动其他Activity的Intent
		Intent intent = new Intent(context, ActivityLayout.class);
		PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
		Notification notify = new Notification.Builder(context)
			//设置打开该通知，该通知自动消失
			.setAutoCancel(true)
			//设置显示在状态栏的通知提示信息
			.setTicker("有新消息")
			//设置通知的图标
			.setSmallIcon(R.drawable.ic_launcher)
			//设置通知内容的标题
			.setContentTitle("一条通知")
			//设置通知的内容
			.setContentText("恭喜你，你加薪！！！")
			//设置使用系统默认的声音，默认LED灯
			//.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE)
				.setDefaults(Notification.DEFAULT_ALL)
			.setWhen(System.currentTimeMillis())

			//设置通知将要启动程序的Intent
			.setContentIntent(pi).build();//4.1以上
				//.getNotification()4.1以下
		//发送通知
		nm.notify(NOTIFICATION_ID, notify);
	}
	
	//为删除通知的按钮的点击事件定义事件处理方法
	public void del(View v)
	{
		nm.cancel(NOTIFICATION_ID);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        //设置窗口特征，启用显示进度的进度条
        requestWindowFeature(Window.FEATURE_PROGRESS);
        //设置窗口特征：启用不显示进度的进度条
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

		setContentView(R.layout.activity_demo);
		context = this;

		//获取系统的NotificationManager服务
		nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

		Button btn_notification = (Button)findViewById(R.id.btn_notification);
		btn_notification.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				send(v);
			}
		});

		Button btn_layout = (Button)findViewById(R.id.btn_layout);
		btn_layout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ActivityLayout.class);
				startActivity(intent);
			}
		});

		Button btn_button = (Button)findViewById(R.id.btn_button);
		btn_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ActivityButton.class);
				startActivity(intent);
			}
		});

		Button btn_clock = (Button)findViewById(R.id.btn_clock);
		btn_clock.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ActivityClock.class);
				startActivity(intent);
			}
		});

		Button btn_imageview = (Button)findViewById(R.id.btn_imageview);
		btn_imageview.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ActivityImageView.class);
				startActivity(intent);
			}
		});

		Button btn_complete = (Button)findViewById(R.id.btn_complete);
		btn_complete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ActivityComplete.class);
				startActivity(intent);
			}
		});

		Button btn_gridView = (Button)findViewById(R.id.btn_gridView);
		btn_gridView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ActivityGridView.class);
				startActivity(intent);
			}
		});

		Button btn_expanda = (Button)findViewById(R.id.btn_expanda);
		btn_expanda.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ActivityExpandableListView.class);
				startActivity(intent);
			}
		});

		Button btn_flipper = (Button)findViewById(R.id.btn_flipper);
		btn_flipper.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ActivityAdapterViewFlipper.class);
				startActivity(intent);
			}
		});

		Button btn_progress = (Button)findViewById(R.id.btn_progress);
		btn_progress.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ActivityProgressBar.class);
				startActivity(intent);
			}
		});

        Button btn_progress_title = (Button)findViewById(R.id.btn_progress_title);
        btn_progress_title.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //显示不带进度的进度条
                setProgressBarIndeterminateVisibility(true);
                //显示不带进度的进度条
                setProgressBarVisibility(true);
                //设置进度条的进度
                setProgress(4500);
            }
        });

		Button btn_viewswitcher = (Button)findViewById(R.id.btn_viewswitcher);
		btn_viewswitcher.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ActivityViewSwitcher.class);
				startActivity(intent);
			}
		});

		Button btn_loadmore = (Button)findViewById(R.id.btn_loadmore);
		btn_loadmore.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ActivityLoadMore.class);
				startActivity(intent);
			}
		});

		Button btn_calendar = (Button)findViewById(R.id.btn_calendar);
		btn_calendar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ActivityCalendar.class);
				startActivity(intent);
			}
		});

        Button btn_async = (Button)findViewById(R.id.btn_async);
        btn_async.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AsyncTaskActivity.class);
                startActivity(intent);
            }
        });

        Button btn_stackview = (Button)findViewById(R.id.btn_stackview);
        btn_stackview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StackViewActivity.class);
                startActivity(intent);
            }
        });

        Button btn_tabbar = (Button)findViewById(R.id.btn_tabbar);
        btn_tabbar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TabbedActivity.class);
                startActivity(intent);
            }
        });

        Button btn_actionBar = (Button)findViewById(R.id.btn_actionBar);
        btn_actionBar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActionBarActivity.class);
                startActivity(intent);
            }
        });

        Button btn_sqlite = (Button)findViewById(R.id.btn_sqlite);
        btn_sqlite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SQLiteActivity.class);
                startActivity(intent);
            }
        });



		((Button)findViewById(R.id.btn_marquee)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, MarqueeActivity.class);
				startActivity(intent);
			}
		});

		((Button)findViewById(R.id.btn_gridView2)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ActivityGridView2.class);
				startActivity(intent);
			}
		});

		((Button)findViewById(R.id.btn_permission)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClassName("com.lanshui.tripmobile", "com.lanshui.tripmobile.MyMainActivity");
				startActivity(intent);
			}
		});

		((Button)findViewById(R.id.btn_Spinner)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ActivitySpinner.class);
				startActivity(intent);
			}
		});

		((Button)findViewById(R.id.btn_webview)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("http://www.baidu.com");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});

		((Button)findViewById(R.id.btn_mwebview)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context, ActivityWebView.class);
				startActivity(intent);
			}
		});

		((Button)findViewById(R.id.btn_fragment)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context, MFragemntActivity.class);
				startActivity(intent);
			}
		});

		/**
		 * ViewPager+PagerTabStrip的使用
		 */
		((Button)findViewById(R.id.btn_viewpager)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context, ActivityViewPager.class);
				startActivity(intent);
			}
		});

		/**
		 * ViewFlipper的使用
		 */
		((Button)findViewById(R.id.btnViewFlipper)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context, ActivityViewFlipper.class);
				startActivity(intent);
			}
		});

		/**
		 * ScrollView的演示
		 */
		((Button)findViewById(R.id.btn_Scrollview)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context, ActivityScrollview.class);
				startActivity(intent);
			}
		});

		/**
		 * Gallery 的使用
		 */
		((Button)findViewById(R.id.btn_gallery)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context, ActivityGallery.class);
				startActivity(intent);
			}
		});

		/**
		 * ListView + AsyncTask + 简单网络请求
		 */
		((Button)findViewById(R.id.btn_mlistview)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context, ActivityMyListView.class);
				startActivity(intent);
			}
		});

		/**
		 * AsyncTask的演示
		 */
		((Button)findViewById(R.id.btn_masynctask)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context, MyAsyncTaskActivity.class);
				startActivity(intent);
			}
		});

		/**
		 * SharedPreferences使用的演示
		 */
		((Button)findViewById(R.id.btn_sharedpreferency)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context, SharedPreferenceActivity.class);
				startActivity(intent);
			}
		});

		/**
		 * 文件读取 写入演示
		 */
		((Button)findViewById(R.id.btn_file)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context, FileReadWriteActivity.class);
				startActivity(intent);
			}
		});

		/**
		 * Volley的演示
		 */
		((Button)findViewById(R.id.btnVolley)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context, VolleyActivity.class);
				startActivity(intent);
			}
		});

		/**
		 * 音频播放的演示
		 */
		((Button)findViewById(R.id.btnMediaPlayer)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context, MediaPlayerActivity.class);
				startActivity(intent);
			}
		});

		/**
		 * AutoScrollViewPager控件的使用
		 */
		((Button)findViewById(R.id.btn_lunbo)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context, AutoScrollViewPagerActivity.class);
				startActivity(intent);
			}
		});

		/**
		 * TabLayout 和 ViewPager的演示
		 */
		((Button)findViewById(R.id.btnManin)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context, MainActivity.class);
				startActivity(intent);
			}
		});

		/**
		 * ListView的演示
		 */
		((Button)findViewById(R.id.btnListView)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context, ListViewActivity.class);
				startActivity(intent);
			}
		});
	}
}
