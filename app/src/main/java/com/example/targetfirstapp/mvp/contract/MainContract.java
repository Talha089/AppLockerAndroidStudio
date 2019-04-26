package com.example.targetfirstapp.mvp.contract;

import android.content.Context;


import com.example.targetfirstapp.base.BasePresenter;
import com.example.targetfirstapp.base.BaseView;
import com.example.targetfirstapp.model.CommLockInfo;

import java.util.List;

public interface MainContract{
    interface View extends BaseView<Presenter>{
        void loadAppInfoSuccess(List<CommLockInfo> list);
    }
    interface Presenter extends BasePresenter{
        void loadAppInfo(Context context, boolean isSort);

        void loadLockAppInfo(Context context);

        void onDestroy();
    }
}