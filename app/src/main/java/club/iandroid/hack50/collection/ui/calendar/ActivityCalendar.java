package club.iandroid.hack50.collection.ui.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.mydemo.R;

public class ActivityCalendar extends Activity
{
	CalendarView cv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_demo);
		
		cv = (CalendarView)findViewById(R.id.myCalendar);
		//为CalendarView组件的日期改变事件添加事件监听器
		cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
			
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,
					int dayOfMonth) {
				Toast.makeText(ActivityCalendar.this, "你生日是："+year+"年"+month+"月"+dayOfMonth+"日", Toast.LENGTH_SHORT).show();
				
			}
		});
	}
}
