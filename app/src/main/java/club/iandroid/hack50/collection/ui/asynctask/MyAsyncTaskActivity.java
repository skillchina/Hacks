package club.iandroid.hack50.collection.ui.asynctask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mydemo.R;
import com.example.mydemo.util.MyAsyncTask;


public class MyAsyncTaskActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_async_task);

        ((Button)findViewById(R.id.btn_dotask)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTask task = new MyAsyncTask();
                task.execute();
            }
        });
    }

}
