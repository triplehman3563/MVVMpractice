package com.example.mvvmpractice;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.CountDownTimer;

public class UserModel extends ViewModel {
    String TAG = "UserModel";
    private CountDownTimer timer;
    MutableLiveData timerValue = new MutableLiveData<Long>();

    private MutableLiveData _finished = new MutableLiveData<Boolean>();

    LiveData<Boolean> finished() {
        return _finished;
    }

    private MutableLiveData<Integer> _seconds = new MutableLiveData<Integer>();

    LiveData<Integer> seconds() {
        return _seconds;
    }


    public UserModel() {
        timerValue.setValue(0);
        _finished.setValue(true);
        _seconds.setValue(0);
    }

    void startTimer() {
        _finished.setValue(false);
        timer = new CountDownTimer((Long) timerValue.getValue(), 1000) {

            @Override
            public void onTick(long l) {
                long timeleft = l / 1000;
                _seconds.setValue((int) timeleft);
            }

            @Override
            public void onFinish() {
                _finished.setValue(true);
            }

        }.start();

    }

    void stopTimer() {
        timer.cancel();
    }
}
