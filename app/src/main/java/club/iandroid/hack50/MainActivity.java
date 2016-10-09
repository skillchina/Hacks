package club.iandroid.hack50;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import club.iandroid.hack50.subject1.TestActivity;


public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private String[] datas;
    private Class[] classes = {
            TestActivity.class
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        addEvent();
        initData();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.mListView);
    }

    private void addEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, classes[position]);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        datas = getResources().getStringArray(R.array.datas);
        mListView.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, datas));

    }
}
