package club.iandroid.hack50.collection.ui.launcher;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.mydemo.ui.listviewdemo.ExpandableListActivityTest;
import com.example.mydemo.db.PreferenceActivityTest;

/**
 * Created by jiaronggeng on 15-pic5-18.
 */
public class OtherLauncherActivity extends LauncherActivity {

    //定义两个Activity的名称
    String[] names = {"设置程序参数","查看星际兵种"};
    //定义两个Activity对应的实现类
    Class<?>[] clazzs = {PreferenceActivityTest.class, ExpandableListActivityTest.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        //设置窗口显示的列表所需的adapter
        setListAdapter(adapter);
    }

    //根据列表返回指定Activity对应的Intent

    @Override
    protected Intent intentForPosition(int position) {
        return new Intent(OtherLauncherActivity.this, clazzs[position]);
    }
}
