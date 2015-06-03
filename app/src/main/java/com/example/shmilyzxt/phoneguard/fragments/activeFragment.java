package com.example.shmilyzxt.phoneguard.fragments;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shmilyzxt.phoneguard.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class activeFragment extends Fragment{

    private Button btn;
    private EditText et;

    public activeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the btn_quickoption_selector for this fragment
        View view = inflater.inflate(R.layout.fragment_active, container, false);
        btn = (Button)view.findViewById(R.id.active_fragment_button);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        et = (EditText)getView().findViewById(R.id.active_fragment_edit);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
