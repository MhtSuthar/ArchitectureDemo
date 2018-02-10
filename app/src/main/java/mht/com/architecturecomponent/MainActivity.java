package mht.com.architecturecomponent;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

import mht.com.architecturecomponent.dao.Word;
import mht.com.architecturecomponent.model.MyViewModel;
import mht.com.architecturecomponent.model.WordViewModel;
import mht.com.architecturecomponent.observer.MyObserver;


public class MainActivity extends AppCompatActivity {

    MyViewModel liveDataTimerViewModel;
    private static final String TAG = "MainActivity";
    WordViewModel wordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        liveDataTimerViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        wordViewModel  = ViewModelProviders.of(this).get(WordViewModel.class);

        //subscribeElapsedTimeObserver();

        getLifecycle().addObserver(new MyObserver());

        wordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> words) {
                for (int i = 0; i < words.size(); i++) {
                    Log.e(TAG, "onChanged: "+words.get(i).getWord());
                }
            }
        });
    }

    public void onClick(View view){
        //Word word = new Word("Hello Time "+System.currentTimeMillis());
        //wordViewModel.insert(word);
        startActivity(new Intent(this, SecondActivity.class));
    }

    private void subscribeElapsedTimeObserver() {
        liveDataTimerViewModel.getData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(@Nullable Long aLong) {
                Log.e(TAG, "onChanged: "+aLong);
                //Toast.makeText(MainActivity.this, ""+aLong, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
