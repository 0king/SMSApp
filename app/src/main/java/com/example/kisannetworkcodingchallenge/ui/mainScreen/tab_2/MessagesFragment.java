package com.example.kisannetworkcodingchallenge.ui.mainScreen.tab_2;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kisannetworkcodingchallenge.R;
import com.example.kisannetworkcodingchallenge.data.model.Message;
import com.example.kisannetworkcodingchallenge.ui.base.IProgressView;

import java.util.List;

public class MessagesFragment extends Fragment implements IMessagesView, IProgressView {

    private MessagesViewModel mViewModel;

    private RecyclerView mMessageListView;
    private MessagesAdapter mAdapter;
    private ProgressBar mProgressBar;

    public MessagesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(MessagesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);
        initUi(view);
        observeData();
        return view;
    }

    private void initUi(View v){
        mProgressBar = v.findViewById(R.id.progressBar);
        mMessageListView = v.findViewById(R.id.recycler_view);
        Context context = v.getContext();
        mMessageListView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        mMessageListView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        mMessageListView.setHasFixedSize(true);
        mAdapter = new MessagesAdapter();
        mMessageListView.setAdapter(mAdapter);
    }

    private void observeData(){
        mViewModel.getAllMessages().observe(getViewLifecycleOwner(), messages -> {
            if (messages==null) showError("Message read error.");
            else if (messages.isEmpty()) showEmpty();
            else showAllMessages(messages);
        });
    }

    @Override
    public void showAllMessages(List<Message> list) {
        mAdapter.updateList(list);
        onFinishLoading();
    }

    private void showEmpty(){
        onFinishLoading();
        Toast.makeText(getContext(), "Nothing found", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        onFinishLoading();
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinishLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

}
