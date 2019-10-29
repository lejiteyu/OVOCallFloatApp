package ovo.Intent.fridayapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FloatService  extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        CustomViewManager.getInstance(this).showFloatViewOnWindow();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CustomViewManager.getInstance(this).onDestroy();
    }
}

