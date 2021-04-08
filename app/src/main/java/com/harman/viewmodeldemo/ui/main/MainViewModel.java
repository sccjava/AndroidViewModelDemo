package com.harman.viewmodeldemo.ui.main;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<MainData> data;

    public MainViewModel(Application application) {
        super(application);
        data = new MutableLiveData<>();
    }

    public void sentCommandToDevice(final long command) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("TAG", "Received command response, post it to UI");
                MainData d = new MainData();
                d.setMessage(command + "");
                data.postValue(d);
            }
        }).start();
    }

    public MutableLiveData<MainData> getData() {
        return data;
    }
}
