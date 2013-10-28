package org.lucas.robolock.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.lucas.robolock.R;
import org.lucas.robolock.RoboLock;
import org.lucas.robolock.SettingsActivity;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.robolock_activity_main);
        RoboLock.init(this);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);


        this.findViewById(R.id.lock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoboLock.lock(MainActivity.this);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lock, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                this.startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RoboLock.onResume(this);
   }

    @Override
    protected  void onPause(){
        super.onPause();
        RoboLock.onPause();
    }
}
