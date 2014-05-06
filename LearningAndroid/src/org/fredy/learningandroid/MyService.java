package org.fredy.learningandroid;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyService extends IntentService {
    private static final String TAG = MyService.class.getSimpleName();
    
    public MyService() {
        super(TAG);
    }
    
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "Do some useless thing...");
    }
}
