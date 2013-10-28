package org.lucas.robolock;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

public class SettingsActivity extends PreferenceActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static final String TAG = SettingsActivity.class.getName();
    public static final String KEY_PREF_ROBOLOCK_AUTO_LOCK_ENABLED = "pref_robolock_auto_lock_enabled";
    public static final String KEY_PREF_ROBOLOCK_AUTO_LOCK_TIME = "pref_robolock_auto_lock_time";
    public static final String KEY_PREF_ROBOLOCK_LAST_ONPAUSE = "pref_robolock_last_onpause_time";
    public static final long DEFAULT_LOCK_TIME = 3 * 60 * 1000;// 3 MINUTES

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        Preference pref = findPreference(KEY_PREF_ROBOLOCK_AUTO_LOCK_TIME);
        pref.setSummary(pref.getPreferenceManager().getSharedPreferences().getString(KEY_PREF_ROBOLOCK_AUTO_LOCK_TIME, "3") + "minutes");

    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d(TAG, "onSharedPreferenceChanged:" + key);
        if (key.equals(KEY_PREF_ROBOLOCK_AUTO_LOCK_TIME)) {
            Preference pref;
            pref = findPreference(key);
            // Set summary to be the user-description for the selected value
            pref.setSummary(sharedPreferences.getString(key, "3") + " minutes");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
        RoboLock.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);

        RoboLock.onPause();
    }
}
