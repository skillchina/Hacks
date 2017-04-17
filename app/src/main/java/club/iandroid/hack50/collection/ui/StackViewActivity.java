package club.iandroid.hack50.collection.ui;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.StackView;

import com.example.mydemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StackViewActivity extends Activity {

    StackView mStackView;
    int[] imageIds = new int[]{
        R.drawable.block_1, R.drawable.block_2, R.drawable.block_3,
        R.drawable.block_4,R.drawable.block_5
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack_view);

        mStackView = (StackView)findViewById(R.id.myStackView);
        //创建一个List对象，List对象的元素是Map
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < imageIds.length; i++){
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("image", imageIds[i]);
            listItems.add(listItem);
        }

        //创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.cell,
                new String[]{"image"}, new int[]{ R.id.image1 });
        mStackView.setAdapter(simpleAdapter);

        ((Button)findViewById(R.id.btn_pre)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //显示上一个组件
                mStackView.showPrevious();
            }
        });

        ((Button)findViewById(R.id.btn_next)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //显示下一个组件
                mStackView.showNext();;
            }
        });
    }


}
