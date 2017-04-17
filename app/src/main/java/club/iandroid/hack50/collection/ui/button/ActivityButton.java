package club.iandroid.hack50.collection.ui.button;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.example.mydemo.R;

public class ActivityButton extends Activity 
{
	ToggleButton toggle;
	Switch switcher;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.button_demo);
		
		toggle = (ToggleButton)findViewById(R.id.toggleButton1);
		switcher = (Switch)findViewById(R.id.switch1);
		
		final LinearLayout test = (LinearLayout)findViewById(R.id.test);
		OnCheckedChangeListener listener = new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					//设置LinearLayout垂直布局
					test.setOrientation(1);
				}else{
					//设置LinearLayout水平布局
					test.setOrientation(0);
				}
				
			}
		};
		
		toggle.setOnCheckedChangeListener(listener);
		switcher.setOnCheckedChangeListener(listener);
	}
}
