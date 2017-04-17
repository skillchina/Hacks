package club.iandroid.hack50.collection.ui.edittext;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import com.example.mydemo.R;

public class ActivityComplete extends Activity 
{
	AutoCompleteTextView actv;
	MultiAutoCompleteTextView mactv;
	//定义字符串数组，作为提示的文本
	String[] books = new String[]{"Java讲义","Android讲义","IOS讲义","Ajax讲义"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.autocomplete_demo);
		
		//创建一个ArrayAdapter，封装数组
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, books);
		actv = (AutoCompleteTextView)findViewById(R.id.auto);
		
		//设置Adapter
		actv.setAdapter(aa);
		
		mactv = (MultiAutoCompleteTextView)findViewById(R.id.multiauto);
		//设置Adapter
		mactv.setAdapter(aa);
		//为MultiAutoCompleteTextView设置分隔符
		mactv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	}
}
