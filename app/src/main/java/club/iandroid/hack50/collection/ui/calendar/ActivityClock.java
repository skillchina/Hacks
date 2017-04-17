package club.iandroid.hack50.collection.ui.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import com.example.mydemo.R;

public class ActivityClock extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clock_demo);
		
		final Button start = (Button)findViewById(R.id.start);
		final Button stop = (Button)findViewById(R.id.stop);
		stop.setEnabled(false);
		
		final Chronometer ch = (Chronometer)findViewById(R.id.chronometer);
		start.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//设置开始计时时间
				ch.setBase(SystemClock.elapsedRealtime());
				//启动计时器
				ch.start();
				start.setEnabled(false);
				stop.setEnabled(true);
			}
		});
		
		stop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ch.stop();
				start.setEnabled(true);
				stop.setEnabled(false);
			}
		});
		
		ch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
			
			@Override
			public void onChronometerTick(Chronometer chronometer) {
				//如果从开始计时到现在超过了20s
				if(SystemClock.elapsedRealtime() - ch.getBase() > 20 * 1000)
				{
					ch.stop();
					start.setEnabled(true);
					stop.setEnabled(false);
				}
				
			}
		});
	}
}
