package com.example.shmilyzxt.phoneguard;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AnalogClock;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;


public class MainActivity extends Activity implements View.OnClickListener ,
        ListFragment.OnFragmentInteractionListener {

    private TextView tv;
    private AnalogClock ac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView)findViewById(R.id.tv);
        tv.setOnClickListener(this);

        activeFragment fragment = new activeFragment();
        FragmentManager fr = getFragmentManager();
        FragmentTransaction ft = fr.beginTransaction();
       // ft.add(R.id.active_fragment,fragment);
        ft.add(R.id.active_fragment, fragment);
        ft.commit();
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

    }
