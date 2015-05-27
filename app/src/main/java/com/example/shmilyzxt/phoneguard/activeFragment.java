package com.example.shmilyzxt.phoneguard;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class activeFragment extends Fragment implements View.OnClickListener{

    private Button btn;
    private AnalogClock ac;
    private EditText et;

    public activeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_active, container, false);
        btn = (Button)view.findViewById(R.id.active_fragment_button);
        btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ac = (AnalogClock)getActivity().findViewById(R.id.analogClock);
        et = (EditText)getView().findViewById(R.id.active_fragment_edit);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
       // ac.setVisibility(View.INVISIBLE);
        FragmentManager fr = getFragmentManager();
        FragmentTransaction ft = fr.beginTransaction();
        ft.replace(R.id.active_fragment, new ListFragment());
        ft.addToBackStack("stack");
        ft.commit();
        Toast.makeText(getActivity(),btn.getText(),Toast.LENGTH_SHORT).show();

        if(et.getText() != null){
            //测试
            Log.wtf("logcat",et.getText().toString());
            Bundle bundle = new Bundle();
            bundle.putString("text",et.getText().toString());
        }else{
            Log.wtf("logcat","editText is empty");
        }


    }
}
