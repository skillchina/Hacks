package club.iandroid.hack50.collection.ui.GridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.mydemo.R;

public class ActivityGridView extends Activity
{
	GridView grid;
	ImageView img;
	int[] images = new int[]{R.drawable.block_1,R.drawable.block_2,
			R.drawable.block_3,R.drawable.block_4,R.drawable.block_5};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview_demo);
		
		//创建一个List对象，List对象的元素是Map
		List<Map<String, Object>> listItems = new ArrayList<Map<String,Object>>();
		for(int i=0;i<images.length;i++){
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("image", images[i]);
			listItems.add(listItem);
		}
		//获取显示的图片ImageView
		img = (ImageView)findViewById(R.id.m_imageview);
		//创建一个SimpleAdapter
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
				R.layout.cell, new String[]{"image"}, new int[]{R.id.image1});
		grid = (GridView)findViewById(R.id.gv);
		//为GridView设置Adapter
		grid.setAdapter(simpleAdapter);
		//添加列表项被选中的监听器
		grid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				img.setImageResource(images[position]);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				img.setImageResource(images[position]);
				
			}
			
		});
	}
}
