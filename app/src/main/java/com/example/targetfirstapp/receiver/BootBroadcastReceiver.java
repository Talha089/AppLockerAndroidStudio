package com.example.targetfirstapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.targetfirstapp.base.AppConstants;
import com.example.targetfirstapp.services.BackgroundManager;
import com.example.targetfirstapp.services.LoadAppListService;
import com.example.targetfirstapp.services.LockService;
import com.example.targetfirstapp.utils.LogUtil;
import com.example.targetfirstapp.utils.SpUtil;


public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(@NonNull Context context, Intent intent) {
        LogUtil.i("Boot service....");
        //TODO: pie compatable done
        // context.startService(new Intent(context, LoadAppListService.class));
        BackgroundManager.getInstance().init(context).startService(LoadAppListService.class);
        if (SpUtil.getInstance().getBoolean(AppConstants.LOCK_STATE, false)) {
            BackgroundManager.getInstance().init(context).startService(LockService.class);
            BackgroundManager.getInstance().init(context).startAlarmManager();
        }
    }
}
