package club.iandroid.hack50.collection.ui.mfragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioGroup;

import com.example.mydemo.R;

/**
 * Created by gabriel on 15/7/11.
 */
public class MFragemntActivity extends FragmentActivity implements Fragmentweixin.OnMClickListener {

    private RadioGroup rgSel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mfragment);
        this.setTitle("微信");
        final FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.mfragme, new Fragmentweixin())
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

        rgSel = (RadioGroup)findViewById(R.id.rgSel);
        rgSel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdo1:
                        fragmentManager.beginTransaction().replace(R.id.mfragme, new Fragmentweixin())
                                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .commit();
                        break;
                    case R.id.rdo2:
                        //像Fragment传值
                        Fragmentlinker fragment = new Fragmentlinker();
                        Bundle bundler = new Bundle();
                        bundler.putString("mid","我要查我的联系人");
                        fragment.setArguments(bundler);
                        fragmentManager.beginTransaction().replace(R.id.mfragme, fragment, "linker")
                                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .commit();

                        break;
                    case R.id.rdo3:

                        break;
                    case R.id.rdo4:

                        break;
                }
            }
        });


    }

    @Override
    public void OnClick(String title) {
        this.setTitle(title);
    }
}
