package club.iandroid.hack50.collection.ui.mfragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mydemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragmentlinker extends Fragment {


    public Fragmentlinker() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmentlinker, container, false);
        Bundle args = getArguments();
        String mid = args.getString("mid");
        Toast.makeText(getActivity(), mid,Toast.LENGTH_LONG).show();
        return view;
    }


}
