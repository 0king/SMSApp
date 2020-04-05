package com.example.kisannetworkcodingchallenge.ui.contactDetails;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kisannetworkcodingchallenge.R;
import com.example.kisannetworkcodingchallenge.data.model.Contact;
import com.example.kisannetworkcodingchallenge.ui.Router;

public class ContactDetailsFragment extends Fragment {

    private TextView mNameView;
    private TextView mPhoneView;
    private Contact mContact;
    private static final String KEY_CONTACT = "contact";

    public ContactDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContact = getArguments().getParcelable(KEY_CONTACT);
        }
    }

    public static ContactDetailsFragment newInstance(Contact contact){
        ContactDetailsFragment f = new ContactDetailsFragment();
        Bundle b = new Bundle();
        b.putParcelable(KEY_CONTACT, contact);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_contact_details, container, false);
        initUi(v);
        bindData();
        return v;
    }

    private void bindData(){
        mNameView.setText(mContact.getFullName());
        mPhoneView.setText(mContact.getPhoneNumber());
    }

    private void initUi(View v){
        mNameView = v.findViewById(R.id.tvName);
        mPhoneView = v.findViewById(R.id.tvPhone);
        v.findViewById(R.id.btnSend).setOnClickListener(v1 -> {
            Router.INSTANCE.showMessageComposeScreen(mContact);
        });
    }

}
