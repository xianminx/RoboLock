package org.lucas.robolock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by lucas on 10/24/13.
 * <p/>
 * To lock an app:
 * In every Activity's onResume, call RoboLock.onResume(), lock the screen if failed to pass the check.
 * In every Activity's onPause, call RoboLock.onPause()
 * <p/>
 * To lock an Activity:
 * call RoboLock.onResume() in this Activity's onResume, and onPause respectively.
 * <p/>
 * To lock another app:
 * register receiver.
 * <p/>
 * To manually lock/unlock:
 * call RoboLock.lock() and RoboLock.unlock().
 *
 * call RoboLock.init(context) before using this class.
 */
public class RoboLock {
    private static long DEFAULT_LOCK_TIME = 3*60*1000;// 3 MINUTES
    private static SharedPreferences lockSP;




    /*
    * Call this method in every Activity's onResume() if you want to lock the whole app. Other wise only call this in the Activity's onResume you want to lock.
    */
    public static void onResume(Activity activity) {
        checkLock(activity);
    }

    /**
     * Call this method in every Activity's onPause() if you want to lock the whole app. Other wise only call this in the Activity's onPause you want to lock.
     */
    public static void onPause() {
        // save the Activity's onPause time.
        lockSP.edit().putLong("onPause", System.currentTimeMillis()).commit();
    }

    /**
     * This brings up the LockActivity. Call finish() the Activity once the user pass the authentication.
     * @param activity
     */
    public static void lock(Activity activity) {
        // show LockActivity
        showLockActivity(activity);
    }



    public static void setLocker() {

    }

    /**
     * Check if this activity can be launched or not.
     * Add this method to every Activity that you want to lock.
     *
     * @param activity The activity to be launched.
     */
    private static void checkLock(Activity activity) {
        long timeElapsed = System.currentTimeMillis()-getLastOnPauseTime(activity);

        if (isLockEnabled(activity) == true && timeElapsed < getAutoLockTime(activity))  {
            showLockActivity(activity);
        }
    }


    private static void showLockActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, LockActivity.class);
        activity.startActivity(intent);
    }

    public static void init(Context context) {
        if (lockSP == null) {
            lockSP = context.getSharedPreferences("robolock", Context.MODE_PRIVATE);
        }
    }

    public static boolean isLockEnabled(Context context) {
        return lockSP.getBoolean("enabled", false);
    }

    private static long getLastOnPauseTime(Context context){
        return lockSP.getLong("onPause", 0L);
    }

    public static long getAutoLockTime(Context context){
    return lockSP.getLong("lockTime", DEFAULT_LOCK_TIME);
    }
    public static void setAutoLockTime(long time) {
        lockSP.edit().putLong("lockTime", time).commit();
    }
}
