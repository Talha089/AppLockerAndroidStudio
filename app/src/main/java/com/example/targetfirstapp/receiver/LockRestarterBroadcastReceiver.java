package com.example.targetfirstapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.targetfirstapp.base.AppConstants;
import com.example.targetfirstapp.services.BackgroundManager;
import com.example.targetfirstapp.services.LockService;
import com.example.targetfirstapp.utils.SpUtil;

public class LockRestarterBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean lockState = SpUtil.getInstance().getBoolean(AppConstants.LOCK_STATE);
        if (intent != null && lockState) {
            String type = intent.getStringExtra("type");
            if (type.contentEquals("lockservice"))
                BackgroundManager.getInstance().init(context).startService(LockService.class);
            else if (type.contentEquals("startlockserviceFromAM")) {
                if (!BackgroundManager.getInstance().init(context).isServiceRunning(LockService.class)) {
                    BackgroundManager.getInstance().init(context).startService(LockService.class);

                }
                //repeat
                BackgroundManager.getInstance().init(context).startAlarmManager();
            }
        }
    }
}

