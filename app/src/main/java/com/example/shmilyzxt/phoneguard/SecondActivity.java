package com.example.shmilyzxt.phoneguard;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Fragment;

import com.example.shmilyzxt.phoneguard.fragments.ListFragment;
import com.example.shmilyzxt.phoneguard.fragments.StaticFragment;
import com.example.shmilyzxt.phoneguard.fragments.activeFragment;


public class SecondActivity extends FragmentActivity implements  ListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        Toast.makeText(SecondActivity.this, "options menu created", Toast.LENGTH_LONG).show();
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

    @Override
    public void onFragmentInteraction(String id) {
        Toast.makeText(SecondActivity.this,id,Toast.LENGTH_LONG).show();
    }
}
