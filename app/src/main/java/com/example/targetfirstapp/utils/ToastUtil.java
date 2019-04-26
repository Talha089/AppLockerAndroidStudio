package com.example.targetfirstapp.utils;

import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.targetfirstapp.LockApplication;

public class ToastUtil {
    @Nullable
    private static Toast mToast = null;

    public static void showToast(String text){
        if (mToast == null){
            mToast = Toast.makeText(LockApplication.getInstance() , text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showLoginToast(String test){
        if (mToast == null){
            mToast = Toast.makeText(LockApplication.getInstance() , test, Toast.LENGTH_LONG);
        } else {
            mToast.setText(test);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}
