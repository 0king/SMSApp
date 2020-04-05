package com.example.kisannetworkcodingchallenge.ui.mainScreen.tab_1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.kisannetworkcodingchallenge.data.MainRepo;
import com.example.kisannetworkcodingchallenge.data.model.Contact;
import com.example.kisannetworkcodingchallenge.data.model.StateLiveData;

import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class ContactsViewModel extends AndroidViewModel {

    private StateLiveData<List<Contact>> list = new StateLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    public ContactsViewModel(@NonNull Application application) {
        super(application);
        loadAllContacts();
    }

    protected StateLiveData<List<Contact>> getAllContacts(){
        return list;
    }

    private void loadAllContacts(){
        Disposable d = MainRepo.INSTANCE
                .getAllContacts(getApplication())
                .subscribe(
                        contacts -> {
                            list.postSuccess(contacts);
                        },
                        err -> {
                            list.postError(err);
                        }
                );
        disposable.add(d);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }

}
