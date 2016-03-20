package com.example.zhenmin.justplay.AIDL;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.example.zhenmin.justplay.AIDL.*;
/**
 * Created by hasee on 2016/2/25.
 */
public class CalculateService extends Service {

    public CalculateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
