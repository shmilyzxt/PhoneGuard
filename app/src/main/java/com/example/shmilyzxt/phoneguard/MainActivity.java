package com.example.shmilyzxt.phoneguard;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AnalogClock;
import android.widget.ShareActionProvider;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar.Tab;

import com.example.shmilyzxt.phoneguard.fragments.ListFragment;
import com.example.shmilyzxt.phoneguard.fragments.StaticFragment;
import com.example.shmilyzxt.phoneguard.fragments.activeFragment;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements View.OnClickListener ,
        ListFragment.OnFragmentInteractionListener {

    private TextView tv;
    private AnalogClock ac;
    private ViewPager viewPager;
    private StaticFragment staticFragment;
    private activeFragment activeFragment;
    private ListFragment listFragment;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        initView();
        //viewPager实现滑动
        setViewPager();
        //ActionBar的添加(要在viewpager的后面，不知道为什么)
        configActionBar();
       //给actonbar添加tabs
        setTabs();
    }

    @Override
    public void onClick(View v) {
        tv = (TextView)findViewById(R.id.tv);
        Toast.makeText(MainActivity.this,tv.getText(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFragmentInteraction(String id) {
        Toast.makeText(MainActivity.this,id,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem shareMenu = menu.findItem(R.id.menu_actionprovider);
        ShareActionProvider shareActionProvider =(ShareActionProvider) shareMenu.getActionProvider();
        shareActionProvider.setShareIntent(createShareIntent());
       // Toast.makeText(MainActivity.this, "options menu created", Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.mobile:
                Toast.makeText(MainActivity.this, "移动", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.telecom:
                Toast.makeText(MainActivity.this, "电信", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.sig:
                Toast.makeText(MainActivity.this, "4G", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*
    设置overflow里的menu item显示图标
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    /*
    为ActionBar的ShareActionProvider创建要分享启动的intent
     */
    private Intent createShareIntent(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        return intent;
    }

    /*
    初始化控件
     */
    private void initView(){
        TextView tv = (TextView)findViewById(R.id.tv);
        tv.setOnClickListener(this);
        staticFragment = new StaticFragment();
        activeFragment = new activeFragment();
        listFragment = new ListFragment();
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        actionBar = getActionBar();

    }
    /*
    设置viewPager
     */
    private void setViewPager(){
        List<Fragment> l = new ArrayList<Fragment>(3);
        l.add(staticFragment);
        l.add(activeFragment);
        l.add(listFragment);
        viewPager.setAdapter(new MyFragemntAdapter(getSupportFragmentManager(),l));
        viewPager.setOnPageChangeListener(new MyPageChangeLisenter());
    }

    /*
    设置anctionbar
     */
    private void configActionBar(){
        actionBar.setHomeButtonEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    /*
    添加并设置actionbar的tabs
     */
    private void setTabs(){
        Tab tab1 = actionBar.newTab()
                .setText("tab1")
                .setIcon(R.drawable.sig)
                .setTabListener(new MyTabListener(this,staticFragment.getClass()));
        Tab tab2 = actionBar.newTab().setText("tab2")
                .setIcon(R.drawable.mboile)
                .setTabListener(new MyTabListener(this,activeFragment.getClass()));
        Tab tab3 = actionBar.newTab().setText("tab3")
                .setIcon(R.drawable.telecom)
                .setTabListener(new MyTabListener(this, listFragment.getClass()));

        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);
    }

    /*
    ActionBar tab TabListener的实现类
     */
    class MyTabListener<T extends Fragment> implements ActionBar.TabListener{

        private final Class<T> tClass;
        private Fragment fg;
        final  private  FragmentActivity mActivity;

        public MyTabListener(FragmentActivity activity,Class<T> fragmentClass){
            tClass = fragmentClass;
            mActivity = activity;
        }

        @Override
        public void onTabSelected(Tab tab, FragmentTransaction ft) {
           viewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            Toast.makeText(mActivity,tab.toString()+" reselected",Toast.LENGTH_SHORT).show();
        }
    }

    /*
    ViewPager的OnPageChangeLister接口实现类
     */
    class MyPageChangeLisenter implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            final ActionBar actionBar = getActionBar();
            actionBar.setSelectedNavigationItem(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /*
    FragmentAdapter的实现类
     */
    class MyFragemntAdapter extends FragmentPagerAdapter{

        List<Fragment> fragmentList;

        public MyFragemntAdapter(FragmentManager myFm,List<Fragment> list){
            super(myFm);
            fragmentList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

}
