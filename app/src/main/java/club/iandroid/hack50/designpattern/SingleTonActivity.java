package club.iandroid.hack50.designpattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import club.iandroid.hack50.R;

/**
 * 1.单例模式
 */
public class SingleTonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_ton);
    }
}
