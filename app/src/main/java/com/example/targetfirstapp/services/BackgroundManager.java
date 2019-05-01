package com.example.targetfirstapp.services;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.targetfirstapp.receiver.LockRestarterBroadcastReceiver;

public class BackgroundManager {

    private static final int period = 15 * 1000; //15 minutes
    private static BackgroundManager mInstance;
    private Context ctx;

    private BackgroundManager() {
    }

    public static BackgroundManager getInstance() {
        if (mInstance == null) {
            mInstance = new BackgroundManager();
            return mInstance;
        } else return mInstance;

    }

    public BackgroundManager init(Context context) {
        ctx = context;
        return this;
    }

    public boolean isServiceRunning(Class<?> serviceClass) {
        checkContext();
        ActivityManager manager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void startService(Class<?> serviceClass){
        checkContext();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            startForegroundService(serviceClass);
        }else
        ctx.startService(new Intent(ctx, serviceClass));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startForegroundService(Class<?> serviceClass){
        ctx.startForegroundService(new Intent(ctx, serviceClass));
    }

    // TODO:Start Alarm Manager
    public void startAlarmManager(){
        checkContext();
        Intent intent = new Intent(ctx,  LockRestarterBroadcastReceiver.class);
        intent.putExtra("type", "startlockserviceFromAM");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx,95374, intent, 0);
        AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+period, pendingIntent);
    }

    // TODO:Stop Alarm Manager
    public void stopAlarmManager() {
        checkContext();
        Intent intent = new Intent(ctx,  LockRestarterBroadcastReceiver.class);
        intent.putExtra("type","startlockservicefromAM");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx,95374,intent,0);
        AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    private void checkContext(){
        if(ctx == null)
            throw new RuntimeException("Context cannot be null: Initialize context first");
    }

    public void stopService(Class<?>serviceClass){
        checkContext();
        if(isServiceRunning(LockService.class))
            ctx.stopService(new Intent(ctx, serviceClass));
    }
}