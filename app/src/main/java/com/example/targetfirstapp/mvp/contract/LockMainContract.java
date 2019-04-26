package com.example.targetfirstapp.mvp.contract;

import android.content.Context;

import com.example.targetfirstapp.base.BasePresenter;
import com.example.targetfirstapp.base.BaseView;
import com.example.targetfirstapp.model.CommLockInfo;
import com.example.targetfirstapp.mvp.p.LockMainPresenter;

import java.util.List;

public interface LockMainContract{
    interface View extends BaseView<Presenter>{

        void loadAppInfoSuccess(List<CommLockInfo>list);
    }

    interface Presenter extends BasePresenter{
        void loadAppInfo(Context context);

        void searchAppInfo(String search, LockMainPresenter.ISearchResultListener listener);

        void onDestroy();
    }
}
