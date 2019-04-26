package com.example.targetfirstapp.activities.fingerprint;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.targetfirstapp.R;
import com.example.targetfirstapp.activities.main.MainActivity;

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintActivity extends Activity {


    FingerprintHandler fingerprintHandler;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_unlock);
        setFinishOnTouchOutside(false);

        final String packageName = getIntent().getStringExtra("app");

        fingerprintHandler=new FingerprintHandler();

        fingerprintHandler.startListening(new FingerprintHandler.Callback() {

            @Override
            public void onAuthenticated() {


                finishAndRemoveTask();
            }

            @Override
            public void onFailed() {

            }

            @Override
            public void onError() {

            }

        });
    }
}
