package mht.com.architecturecomponent.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import mht.com.architecturecomponent.dao.Word;
import mht.com.architecturecomponent.repo.WordRepository;


public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    // private MutableLiveData<List<Word>> elapsedTime = new MutableLiveData<>();

    private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }
}