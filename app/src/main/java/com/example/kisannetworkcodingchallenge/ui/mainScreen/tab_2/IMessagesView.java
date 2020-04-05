package com.example.kisannetworkcodingchallenge.ui.mainScreen.tab_2;

import com.example.kisannetworkcodingchallenge.data.model.Contact;
import com.example.kisannetworkcodingchallenge.data.model.Message;

import java.util.List;

public interface IMessagesView {
    void showAllMessages(List<Message> list);
    void showError(String message);
}
