package mht.com.architecturecomponent.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Mohit-Anjali on 01-Feb-18.
 */

public class MyViewModel extends ViewModel {

    private MutableLiveData<Long> elapsedTime = new MutableLiveData<>();
    private static final int ONE_SECOND = 1000;
    private long initialTime;
    private static final String TAG = "MyViewModel";

    public LiveData<Long> getData() {
        return elapsedTime;
    }

    public MyViewModel(){
        Log.e(TAG, "MyViewModel: ");
        initialTime = SystemClock.elapsedRealtime();
        Timer timer = new Timer();

        // Update the elapsed time every second.
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final long newValue = (SystemClock.elapsedRealtime() - initialTime) / 1000;
                // setValue() cannot be called from a background thread so post to main thread.
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        elapsedTime.setValue(newValue);

                    }
                });
            }
        }, ONE_SECOND, ONE_SECOND);
    }
}
