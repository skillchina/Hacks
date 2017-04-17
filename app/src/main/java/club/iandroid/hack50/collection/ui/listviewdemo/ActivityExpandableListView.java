package club.iandroid.hack50.collection.ui.listviewdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mydemo.R;

public class ActivityExpandableListView extends Activity
{
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandablelistview_demo);
		context = this;
		
		//创建一个BaseExpandableListAdapter对象
		ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
			int[] logos = new int[]
			{
				R.drawable.block_1,
				R.drawable.block_2,
				R.drawable.block_3
			};
			private String[] armTypes = new String[]{"神族兵种","虫族兵种","人族兵种"};
			private String[][] arms = new String[][]
			{
					{"狂战士","龙骑士"},
					{"小狗","刺蛇","飞龙"},
					{"机枪兵","护士MM","幽灵"}
			};
			@Override
			public boolean isChildSelectable(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				LinearLayout ll = new LinearLayout(context);
				ll.setOrientation(LinearLayout.VERTICAL);
				ImageView logo = new ImageView(context);
				logo.setImageResource(logos[groupPosition]);
				ll.addView(logo);
				TextView tv = getTextView();
				tv.setText(getGroup(groupPosition).toString());
				ll.addView(tv);
				return ll;
			}
			
			@Override
			public long getGroupId(int groupPosition) {
				
				return groupPosition;
			}
			
			@Override
			public int getGroupCount() {
				// TODO Auto-generated method stub
				return armTypes.length;
			}
			
			@Override
			public Object getGroup(int groupPosition) {
				// TODO Auto-generated method stub
				return armTypes[groupPosition];
			}
			
			@Override
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				return arms[groupPosition].length;
			}
			
			private TextView getTextView()
			{
				LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
				TextView tv = new TextView(context);
				tv.setLayoutParams(lp);
				tv.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
				tv.setPadding(36, 0, 0, 0);
				tv.setTextSize(20);
				return tv;
			}
			
			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				TextView textView = getTextView();
				textView.setText(getChild(groupPosition,childPosition).toString());
				return textView;
			}
			
			@Override
			public long getChildId(int groupPosition, int childPosition) {
				
				return childPosition;
			}
			
			@Override
			public Object getChild(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return arms[groupPosition][childPosition];
			}
		};
		
		ExpandableListView elv = (ExpandableListView)findViewById(R.id.ex_listview);
		elv.setAdapter(adapter);
	}
}
