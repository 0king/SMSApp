package com.example.kisannetworkcodingchallenge.ui.mainScreen.tab_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kisannetworkcodingchallenge.R;
import com.example.kisannetworkcodingchallenge.data.model.Contact;
import com.example.kisannetworkcodingchallenge.ui.Router;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactHolder> {

    private List<Contact> mList;

    ContactsAdapter(){}
    ContactsAdapter(List<Contact> list){
        mList = list;
    }

    protected void setList(List<Contact> list){
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        if (mList!=null) holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    protected class ContactHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        ContactHolder(View view){
            super(view);
            tvName = view.findViewById(R.id.tvName);
            view.setOnClickListener(v ->
                    Router.INSTANCE.showContactDetailsScreen(mList.get(getAdapterPosition())));
        }

        void bind(Contact contact){
            tvName.setText(contact.getFullName());
        }
    }
}
