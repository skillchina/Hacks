package club.iandroid.hack50.collection.ui.GridView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.mydemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityGridView2 extends Activity
{
	GridView grid;
	ImageView img;
	int[] images = new int[]{R.drawable.address_book,R.drawable.calendar,
			R.drawable.camera,R.drawable.clock,R.drawable.games_control,
			R.drawable.messenger,R.drawable.ringtone,
			R.drawable.settings,R.drawable.speech_balloon,R.drawable.weather,
	R.drawable.world, R.drawable.youtube};

	String[] iconName = {"icon1","icon2","icon3","icon4","icon5",
			"icon6","icon7","icon8","icon9","icon10","icon11","icon12"};

	private List<Map<String, Object>> listItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview_demo2);
		
		//创建一个List对象，List对象的元素是Map
		listItems = new ArrayList<Map<String,Object>>();
		for(int i=0;i<images.length;i++){
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("image", images[i]);
			listItem.put("icon",iconName[i]);
			listItems.add(listItem);
		}
		//获取显示的图片ImageView
		img = (ImageView)findViewById(R.id.m_imageview);
		//创建一个SimpleAdapter
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
				R.layout.cell, new String[]{"image","icon"}, new int[]{R.id.image1,R.id.micon});
		grid = (GridView)findViewById(R.id.gv);
		//为GridView设置Adapter
		grid.setAdapter(simpleAdapter);

	}
}
