package com.example.kisannetworkcodingchallenge.ui.composeScreen;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kisannetworkcodingchallenge.R;
import com.example.kisannetworkcodingchallenge.data.MainRepo;
import com.example.kisannetworkcodingchallenge.data.model.Contact;
import com.example.kisannetworkcodingchallenge.data.model.DataStatus;
import com.example.kisannetworkcodingchallenge.data.model.Message;

public class ComposeNewMessageFragment extends Fragment {

    private static final String ARG_CONTACT = "contact";

    private Contact mContact;
    private TextView mMessageView;
    private TextView mResponseView;
    private ProgressBar mBar;
    private Button mSendButton;
    private NewMessageViewModel mViewModel;

    public ComposeNewMessageFragment() {
        // Required empty public constructor
    }

    public static ComposeNewMessageFragment newInstance(Contact contact) {
        ComposeNewMessageFragment f = new ComposeNewMessageFragment();
        Bundle b = new Bundle();
        b.putParcelable(ARG_CONTACT, contact);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContact = getArguments().getParcelable(ARG_CONTACT);
        }
        mViewModel = new ViewModelProvider(this).get(NewMessageViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compose_new_message, container, false);
        initUi(view);
        bindData();
        return view;
    }

    private void onHitSend(){
        mViewModel.onHitSend(createMessage(), mContact.getPhoneNumber(), mMessageView.getText().toString());
        //disbale button
        mSendButton.setEnabled(false);
        //show loading
        mBar.setVisibility(View.VISIBLE);
        //hide
        mResponseView.setVisibility(View.GONE);
    }

    private void initUi(View view){
        mMessageView = view.findViewById(R.id.tvMessage);
        mResponseView = view.findViewById(R.id.tvResponse);
        mSendButton = view.findViewById(R.id.btnSend);
        mBar = view.findViewById(R.id.progressBar);
        mSendButton.setOnClickListener(v -> onHitSend());
        //hide
        mBar.setVisibility(View.GONE);
        mResponseView.setVisibility(View.GONE);
    }

    private void bindData(){
        mViewModel.getOtp().observe(getViewLifecycleOwner(), otp -> {
            mMessageView.setText("Hi! Your OTP is "+otp);
        });

        mViewModel.getSmsServerResponse().observe(getViewLifecycleOwner(), res -> {
            if (res == DataStatus.SUCCESS){
                mResponseView.setText("Success");
            }else {
                mResponseView.setText("Failed");
            }
            updateUi();
        });
    }

    private void updateUi(){
        mSendButton.setEnabled(true);
        mBar.setVisibility(View.GONE);
        mResponseView.setVisibility(View.VISIBLE);
    }

    private Message createMessage(){
        return new Message(System.currentTimeMillis(),
                mViewModel.getOtp().getValue(),
                mContact.getFullName());
    }

}
