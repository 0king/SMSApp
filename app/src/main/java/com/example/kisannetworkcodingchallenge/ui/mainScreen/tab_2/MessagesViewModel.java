package com.example.kisannetworkcodingchallenge.ui.mainScreen.tab_2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.kisannetworkcodingchallenge.data.MainRepo;
import com.example.kisannetworkcodingchallenge.data.model.Contact;
import com.example.kisannetworkcodingchallenge.data.model.Message;
import com.example.kisannetworkcodingchallenge.data.model.StateLiveData;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MessagesViewModel extends AndroidViewModel {

    private LiveData<List<Message>> list;

    public MessagesViewModel(@NonNull Application application) {
        super(application);
        loadAllMessages();
    }

    public LiveData<List<Message>> getAllMessages(){
        return list;
    }

    private void loadAllMessages(){
        list = MainRepo.INSTANCE.getAllMessages(getApplication());
    }

}
