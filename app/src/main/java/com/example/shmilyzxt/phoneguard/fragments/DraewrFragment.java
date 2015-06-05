package com.example.shmilyzxt.phoneguard.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.shmilyzxt.phoneguard.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DraewrFragment extends Fragment implements View.OnClickListener{

    View mMenu_item_quests;
    View mMenu_item_opensoft;
    View mMenu_item_blog;
    View mMenu_item_gitapp;
    View mMenu_item_rss;
    View mMenu_item_setting;
    View mMenu_item_exit;
    View view;

    public DraewrFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_draewr, container, false);
        return view;
    }

    private void initView(){
        mMenu_item_quests = view.findViewById(R.id.menu_item_quests);
        mMenu_item_opensoft = view.findViewById(R.id.menu_item_opensoft);
        mMenu_item_blog = view.findViewById(R.id.menu_item_blog);
        mMenu_item_gitapp = view.findViewById(R.id.menu_item_gitapp);
        mMenu_item_rss = view.findViewById(R.id.menu_item_rss);
        mMenu_item_setting = view.findViewById(R.id.menu_item_setting);
        mMenu_item_exit = view.findViewById(R.id.menu_item_exit);
        mMenu_item_quests.setOnClickListener(this);
        mMenu_item_opensoft.setOnClickListener(this);
        mMenu_item_blog.setOnClickListener(this);
        mMenu_item_gitapp.setOnClickListener(this);
        mMenu_item_rss.setOnClickListener(this);
        mMenu_item_setting.setOnClickListener(this);
        mMenu_item_exit.setOnClickListener(this);
    }

    public void onClick(View v){
        int id = v.getId();
        switch (id){
            case R.id.menu_item_quests:
                Toast.makeText(getActivity(),"第一个被点",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_opensoft:
                //转到SocketActivity
                Intent intent = new Intent(getActivity(),SocketActivity.class);
                getActivity().startActivity(intent);

                break;
            case R.id.menu_item_blog:

                break;
            case R.id.menu_item_gitapp:

                break;
            case R.id.menu_item_setting:

                break;
            case R.id.menu_item_exit:
                // 杀死该应用进程
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
                break;
            case R.id.menu_item_rss:
                break;
            default:
                Toast.makeText(getActivity(),"你在干什么",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /*
    定义回调函数接口，用于activity实现
     */
    public interface DrawerFragmentCallBacks{
        void onDrawerFragmentItemSelected(int position);
    }
}
