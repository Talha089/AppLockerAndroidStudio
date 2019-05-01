package com.example.targetfirstapp.mvp.contract;



import com.example.targetfirstapp.base.BasePresenter;
import com.example.targetfirstapp.base.BaseView;
import com.example.targetfirstapp.model.LockStage;
import com.example.targetfirstapp.widget.LockPatternView;

import java.util.List;


public interface GestureCreateContract {

    interface View extends BaseView<MainContract.Presenter>{
        void updateUiStage(LockStage stage);

        void updateChosenPattern(List<LockPatternView.Cell> mChosenPattern);

        void updateLockTip(String text, boolean isToast);

        void setHeaderMessage(int headerMessage);

        void lockPatternViewConfiguration(boolean patternEnabled, LockPatternView.DisplayMode displayMode);

        void Introduction();

        void HelpScreen();

        void ChoiceTooShort();

        void moveToStatusTwo();

        void clearPattern();

        void ConfirmWrong();

        void ChoiceConfirmed();

    }

    interface Presenter extends BasePresenter{
        void updateStage(LockStage stage);

        void onPatternDetected(List<LockPatternView.Cell>pattern, List<LockPatternView.Cell> mChosenPattern, LockStage mUiStage);

        void onDestroy();
    }

   }
   