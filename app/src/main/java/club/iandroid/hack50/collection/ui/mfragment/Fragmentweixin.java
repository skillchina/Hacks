package club.iandroid.hack50.collection.ui.mfragment;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mydemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragmentweixin extends Fragment {


    private static final String TAG = "Fragmentweixin";
    private EditText txt;
    OnMClickListener mClickListener;

    public Fragmentweixin() {
        // Required empty public constructor
    }

    //当Fragment被添加到Activity时候会调用这个方法，并且只会调用一次
    @Override
    public void onAttach(Activity activity) {
        mClickListener = (OnMClickListener)activity;
        super.onAttach(activity);
        Log.i(TAG, "onAttach-------");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate-------");
    }

    //每次创建都会绘制Fagment的View组件时回调该方法
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView-------");
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragmentweixin, container, false);
        txt = (EditText)view.findViewById(R.id.txttitle);
        ((Button)view.findViewById(R.id.btn_change)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.OnClick(txt.getText().toString());
            }
        });
        return view;
    }

    //当Fragment所在的Activity启动完成后调用
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated-------");
    }

    /**
     * 启动Fragment
     */
    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart-------");
    }

    /**
     * 恢复Fragment时被回调，onStart被调用时，此方法也一定会被回调
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume-------");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause-------");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop-------");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView-------");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy-------");
    }

    /**
     * Fragment从Activity删除时，会被回调
     */
    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach-------");
    }

    public interface OnMClickListener{
        void OnClick(String title);
    }
}
