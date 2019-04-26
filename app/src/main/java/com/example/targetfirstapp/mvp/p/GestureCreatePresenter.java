package com.example.targetfirstapp.mvp.p;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.targetfirstapp.R;
import com.example.targetfirstapp.model.LockStage;
import com.example.targetfirstapp.mvp.contract.GestureCreateContract;
import com.example.targetfirstapp.utils.LockPatternUtils;
import com.example.targetfirstapp.widget.LockPatternView;

import java.util.ArrayList;
import java.util.List;


public class GestureCreatePresenter implements GestureCreateContract.Presenter {
    private GestureCreateContract.View mView;
    private Context mContext;

    public GestureCreatePresenter(GestureCreateContract.View view, Context context){
        mView = view;
        mContext = context;
    }


    @Override
    public void updateStage(@NonNull LockStage stage) {
        mView.updateUiStage(stage);  //UiStage
        if (stage == LockStage.ChoiceTooShort){
            mView.updateLockTip(mContext.getResources().getString(stage.headerMessage, LockPatternUtils.MIN_LOCK_PATTERN_SIZE) , true);
        } else {
            if(stage.headerMessage == R.string.lock_need_to_unlock_wrong){
                mView.updateLockTip(mContext.getResources().getString(R.string.lock_need_to_unlock_wrong), true);
                mView.setHeaderMessage(R.string.lock_recording_intro_header);
            }
            else{
                mView.setHeaderMessage(stage.headerMessage);
            }
        }
        // same for whether the pattern is enabled
        mView.lockPatternViewConfiguration(stage.patternEnabled, LockPatternView.DisplayMode.Correct);

        switch (stage){
            case Introduction:
                mView.Introduction();
                break;
            case HelpScreen:
                mView.HelpScreen();
                break;
            case ChoiceTooShort:
                mView.ChoiceTooShort();
                break;
            case FirstChoiceValid:
                updateStage(LockStage.NeedToConfirm);
                mView.moveToStatusTwo();
                break;
            case NeedToConfirm:
                mView.clearPattern();
                break;
            case ConfirmWrong:
                mView.ConfirmWrong();
                break;
            case ChoiceConfirmed:
                mView.ChoiceConfirmed();
                break;
        }
    }

    @Override
    public void onPatternDetected(@NonNull List<LockPatternView.Cell> pattern, @NonNull List<LockPatternView.Cell> mChosenPattern, LockStage mUiStage) {
        if (mUiStage == LockStage.NeedToConfirm){
            if (mChosenPattern == null)
                throw new IllegalStateException("null chosen pattern in stage need to confirm");
                if (mChosenPattern.equals(pattern)){
                    updateStage(LockStage.ChoiceConfirmed);
                }else {
                    updateStage(LockStage.ConfirmWrong);
            }
        }
        else if(mUiStage == LockStage.ConfirmWrong){
            if (pattern.size() < LockPatternUtils.MIN_LOCK_PATTERN_SIZE){
                updateStage(LockStage.ChoiceTooShort);
            } else {
                if (mChosenPattern.equals(pattern)){
                    updateStage(LockStage.ChoiceConfirmed);
                } else
                {
                    updateStage(LockStage.ConfirmWrong);
                }
            }
        } else if (mUiStage == LockStage.Introduction || mUiStage == LockStage.ChoiceTooShort){
            if (pattern.size() < LockPatternUtils.MIN_LOCK_PATTERN_SIZE){
                updateStage(LockStage.ChoiceTooShort);
            } else {
                mChosenPattern = new ArrayList<>(pattern);
                mView.updateChosenPattern(mChosenPattern);
                updateStage(LockStage.FirstChoiceValid);
            }
        } else {
            throw new IllegalStateException("Unexpected stage" + mUiStage + " when " + "entering the pattern");
        }
    }

    @Override
    public void onDestroy() {

    }
}
