package com.example.kisannetworkcodingchallenge.ui.mainScreen.tab_1;

import com.example.kisannetworkcodingchallenge.data.model.Contact;

import java.util.List;

public interface IContactsView {
    void showAllContacts(List<Contact> list);
    void showError(String message);
}
