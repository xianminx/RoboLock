package org.lucas.robolock;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class LockActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.robolock_activity_lock);
        findViewById(R.id.unlock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unlock();
            }
        });
    }

    private void unlock(){
        finish();
    }
}
