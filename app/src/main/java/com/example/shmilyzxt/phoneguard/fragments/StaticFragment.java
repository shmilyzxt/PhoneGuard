package com.example.shmilyzxt.phoneguard.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import com.loopj.android.http.*;

import com.example.shmilyzxt.phoneguard.R;

import org.apache.http.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class StaticFragment extends Fragment {

    static AsyncHttpClient client = new AsyncHttpClient();
    final static String url = "http://www.oschina.net/action/api/news_list";

    private WebView webView;
    private View view;

    public StaticFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the btn_quickoption_selector for this fragment
        view = inflater.inflate(R.layout.fragment_static, container, false);
        return view;
    }

    private void initView(){
        //初始化view相关操作

        //AsyncHttpClient方式获取数据
        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {

            }

            @Override
            public void onSuccess(int i, Header[] headers, String s) {
                webView = (WebView)view.findViewById(R.id.webView);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setDefaultTextEncodingName("UTF-8");
                webView.getSettings().setAllowContentAccess(true);
                webView.getSettings().setSupportZoom(true);

                webView.loadData(s, "text/html; charset=UTF-8", null);
            }
        });
    }


}
