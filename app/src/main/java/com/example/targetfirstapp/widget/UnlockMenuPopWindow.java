package com.example.targetfirstapp.widget;

import android.app.Activity;
import android.view.View;
import android.widget.PopupWindow;

public class UnlockMenuPopWindow extends PopupWindow implements View.OnClickListener{

    public UnlockMenuPopWindow(final Activity context, String pkgName, boolean isShowCheckboxPattern){
    super(context);

    }

    @Override
    public void onClick(View v) {

    }
}
