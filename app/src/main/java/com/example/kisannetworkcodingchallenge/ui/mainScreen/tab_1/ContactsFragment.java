package com.example.kisannetworkcodingchallenge.ui.mainScreen.tab_1;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
import com.example.kisannetworkcodingchallenge.data.model.Contact;
import com.example.kisannetworkcodingchallenge.data.model.Resource;
import com.example.kisannetworkcodingchallenge.ui.base.IProgressView;

import java.util.List;

public class ContactsFragment extends Fragment implements IContactsView, IProgressView {

    private ContactsViewModel mViewModel;

    private RecyclerView mContactsListView;
    private ContactsAdapter mAdapter;
    private ProgressBar mProgressBar;

    private Observer<Resource> mListObserver = (response) -> {
        switch (response.getStatus()){
            case ERROR: {
                showAllContacts(null);
                showError(response.getError().toString());
            }
            break;
            case SUCCESS: {
                List<Contact> newList = (List<Contact>) response.getData();
                if (newList.isEmpty()) //UI logic
                    Toast.makeText(getContext(), "Nothing found", Toast.LENGTH_SHORT).show();
                showAllContacts(newList);
            }
            break;
        }
    };

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(ContactsViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        initUi(view);
        observeData();
        return view;
    }

    private void initUi(View v){
        mProgressBar = v.findViewById(R.id.progressBar);
        mContactsListView = v.findViewById(R.id.recycler_view);
        Context context = v.getContext();
        mContactsListView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        mContactsListView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        mContactsListView.setHasFixedSize(true);
        mAdapter = new ContactsAdapter();
        mContactsListView.setAdapter(mAdapter);
    }

    private void observeData(){
        mViewModel.getAllContacts().observe(getViewLifecycleOwner(), mListObserver);
    }

    @Override
    public void showAllContacts(List<Contact> list) {
        mAdapter.setList(list);
        onFinishLoading();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), "Error getting contacts", Toast.LENGTH_SHORT).show();
        onFinishLoading();
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
