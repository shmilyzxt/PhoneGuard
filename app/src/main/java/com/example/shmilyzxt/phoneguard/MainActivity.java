package com.example.shmilyzxt.phoneguard;

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
import android.widget.AnalogClock;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener ,ListFragment.OnFragmentInteractionListener {

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
        ft.replace(R.id.active_fragment,fragment);
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        tv = (TextView)findViewById(R.id.tv);
        Toast.makeText(MainActivity.this,tv.getText(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
