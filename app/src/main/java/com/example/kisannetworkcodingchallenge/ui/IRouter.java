package com.example.kisannetworkcodingchallenge.ui;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.example.kisannetworkcodingchallenge.data.model.Contact;

public interface IRouter {
    void handleError(Context c, Throwable throwable);
    void showMainScreen();
    void showContactDetailsScreen(Contact contact);
    void showMessageComposeScreen(Contact contact);
}
