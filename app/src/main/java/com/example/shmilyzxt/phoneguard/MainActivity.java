package com.example.shmilyzxt.phoneguard;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AnalogClock;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar.Tab;

import com.example.shmilyzxt.phoneguard.fragments.FourFragment;
import com.example.shmilyzxt.phoneguard.fragments.ListFragment;
import com.example.shmilyzxt.phoneguard.fragments.StaticFragment;
import com.example.shmilyzxt.phoneguard.fragments.activeFragment;
import com.example.shmilyzxt.phoneguard.fragments.ThreeFragment;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements ListFragment.OnFragmentInteractionListener {

    private ViewPager viewPager;
    private StaticFragment staticFragment;
    private activeFragment activeFragment;
    private ListFragment listFragment;
    private ThreeFragment threeFragment;
    private ActionBar actionBar;
    private FragmentTabHost tabHost;
    private FourFragment fourFragment;

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
        //设置tabHost
        setTabHost();
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
        staticFragment = new StaticFragment();
        activeFragment = new activeFragment();
        threeFragment = new ThreeFragment();
        fourFragment = new FourFragment();
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        actionBar = getActionBar();
        tabHost = (FragmentTabHost)findViewById(R.id.tabhost);

    }
    /*
    设置viewPager
     */
    private void setViewPager(){
        List<Fragment> l = new ArrayList<Fragment>();
        l.add(staticFragment);
        l.add(activeFragment);
        l.add(threeFragment);
        l.add(fourFragment);
        viewPager.setAdapter(new MyFragemntAdapter(getSupportFragmentManager(), l));
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
    设置FragmentTabHost
     */
    private void setTabHost(){
        //定义class数组存放fragment
        Class fragmentArray[] = {StaticFragment.class, activeFragment.class,ThreeFragment.class,FourFragment.class};
        //定义tab标题数组
        String tabTittles[] = {"首页","新闻","发现","朋友"};
        //定义tabs图片数组
        int tabDrawables[] = {R.drawable.tab_index_selectror,R.drawable.tab_news_selectror,R.drawable.tab_message_selectror,R.drawable.tab_friend_selectror};
        /*
        特别注意，如果FragmentTabHost单独使用，这里的第三个参数为放fragment的layout的id
                如果FragmentTabHost结合PagerView使用，这里的第三各参数为PagerView的id
         */
        tabHost.setup(this, getSupportFragmentManager(), R.id.viewPager);
        tabHost.setOnTabChangedListener(new FragmentTabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int positon = tabHost.getCurrentTab();
                viewPager.setCurrentItem(positon);
            }
        });
        for(int i=0;i<fragmentArray.length;i++){
            View indactor = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_item, null);
            ImageView imageView = (ImageView)indactor.findViewById(R.id.imageview1);
            imageView.setImageResource(tabDrawables[i]);
            TextView textView = (TextView)indactor.findViewById(R.id.textview1);
            textView.setText(tabTittles[i]);
            TabSpec tabSpec = tabHost.newTabSpec(tabTittles[i]);
            tabSpec.setIndicator(indactor);
            tabHost.addTab(tabSpec, fragmentArray[i], null);
            tabHost.setTag(i);
        }
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
                .setTabListener(new MyTabListener(this, threeFragment.getClass()));
        Tab tab4 = actionBar.newTab().setText("tab4")
                .setIcon(R.drawable.telecom)
                .setTabListener(new MyTabListener(this, fourFragment.getClass()));

        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);
        actionBar.addTab(tab4);
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
           tabHost.setCurrentTab(tab.getPosition());
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
            actionBar.setSelectedNavigationItem(position);//actionbar的tab与viewpager联动
            tabHost.setCurrentTab(position);//fragmenttabhost的tab与viewpager联动
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
