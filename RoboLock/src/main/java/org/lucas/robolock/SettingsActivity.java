package org.lucas.robolock;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);



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
