package mht.com.architecturecomponent;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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


public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    WordViewModel wordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordViewModel  = ViewModelProviders.of(this).get(WordViewModel.class);
    }

    public void onClick(View view){
        Word word = new Word("Hello Time "+System.currentTimeMillis());
        wordViewModel.insert(word);
    }
}
