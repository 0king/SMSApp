package com.example.kisannetworkcodingchallenge.data;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.kisannetworkcodingchallenge.data.model.Contact;
import com.example.kisannetworkcodingchallenge.data.model.Message;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface IRepo {
    Single<List<Contact>> getAllContacts(Context context);
    LiveData<List<Message>> getAllMessages(Application a);
    void insertMessage(Application a, Message m);
}
