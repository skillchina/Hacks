package club.iandroid.hack50.collection.data;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mydemo.R;

public class SharedPreferenceActivity extends Activity {

    private EditText txt_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        txt_info = (EditText)findViewById(R.id.txt_info);
        //SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(SharedPreferenceActivity.this);
        SharedPreferences shared = getSharedPreferences("SharedInfo", MODE_PRIVATE);
        final SharedPreferences.Editor editor = shared.edit();

        String info = shared.getString("info", "");
        txt_info.setText(info);

        ((Button)findViewById(R.id.btnSave)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("info", txt_info.getText().toString());
                editor.commit();
            }
        });

        ((Button)findViewById(R.id.btnClear)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove("info");
                editor.commit();
            }
        });
    }



}
