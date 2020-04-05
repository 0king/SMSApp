package com.example.kisannetworkcodingchallenge.ui;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.kisannetworkcodingchallenge.R;
import com.example.kisannetworkcodingchallenge.data.model.Contact;
import com.example.kisannetworkcodingchallenge.ui.composeScreen.ComposeNewMessageFragment;
import com.example.kisannetworkcodingchallenge.ui.contactDetails.ContactDetailsFragment;
import com.example.kisannetworkcodingchallenge.ui.mainScreen.TabsFragment;

public enum Router implements IRouter {

    INSTANCE;

    private FragmentManager fm;

    public void init(FragmentManager fm){
        this.fm = fm;
    }

    @Override
    public void handleError(Context c, Throwable throwable) {
        Toast.makeText(c, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private static final String TAG_TABS = "TABS";
    private static final String TAG_DETAIL = "contact_detail";
    private static final String TAG_COMPOSE = "new_message";

    @Override
    public void showMainScreen() {
        if (fm==null)
            return;
        //throw new Exception("Please initialize the Navigator");

        Fragment fragment = fm.findFragmentByTag(TAG_TABS);
        if (fragment==null){
            fragment = new TabsFragment();
        }
        fm.beginTransaction()
                .replace(R.id.main_container, fragment, TAG_TABS)
                .commit();
    }

    @Override
    public void showContactDetailsScreen(Contact contact) {
        if (fm==null)
            return;
        //throw new Exception("Please initialize the Navigator");

        Fragment fragment = fm.findFragmentByTag(TAG_DETAIL);
        if (fragment==null){
            fragment = ContactDetailsFragment.newInstance(contact);
        }
        fm.beginTransaction()
                .replace(R.id.main_container, fragment, TAG_DETAIL)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showMessageComposeScreen(Contact contact) {
        if (fm==null)
            return;
        //throw new Exception("Please initialize the Navigator");

        Fragment fragment = fm.findFragmentByTag(TAG_COMPOSE);
        if (fragment==null){
            fragment = ComposeNewMessageFragment.newInstance(contact);
        }
        fm.beginTransaction()
                .replace(R.id.main_container, fragment, TAG_COMPOSE)
                .addToBackStack(null)
                .commit();
    }
}
