package club.iandroid.hack50.collection.ui.spinner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mydemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ActivitySpinner extends Activity implements AdapterView.OnItemSelectedListener {

    TextView mInfo;
    Spinner mSpinner;
    List<String> mDatas;
    ArrayAdapter<String> adapter;

    SimpleAdapter custAdapter;
    List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_spinner);
        mInfo = (TextView)findViewById(R.id.mInfo);
        mSpinner = (Spinner)findViewById(R.id.mSpinner);

        //initDefaultSpinner();
        initCustSpinner();
    }

    private void initCustSpinner(){
        //pic1、设置数据源
        list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("icon", R.drawable.address_book);
        map.put("title", "北京");
        list.add(map);

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("icon", R.drawable.games_control);
        map1.put("title", "南京");
        list.add(map1);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("icon", R.drawable.youtube);
        map2.put("title", "苏州");
        list.add(map);

        //pic2、新建适配器
        custAdapter = new SimpleAdapter(this, list, R.layout.m_spinner1,
                new String[]{"icon","title"},
                new int[]{R.id.mImg, R.id.txtTitle});
        custAdapter.setDropDownViewResource(R.layout.m_spinner1);
        mSpinner.setAdapter(custAdapter);
        mSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = list.get(position);
                mInfo.setText(map.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //加载默认Spinner
    private void initDefaultSpinner(){
        mDatas = new ArrayList<String>();
        //pic1、设置数据源
        mDatas.add("北京");
        mDatas.add("上海");
        mDatas.add("南京");
        mDatas.add("苏州");

        //pic2、新建ArrayAdapter(数组适配器)
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mDatas);

        //pic3、adapter设置一个下拉列表样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //pic4、spinner加载适配器
        mSpinner.setAdapter(adapter);

        //pic5、设置监听器
        mSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String cityName = adapter.getItem(position);
        //String city = mDatas.get(position);
        mInfo.setText(cityName);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
