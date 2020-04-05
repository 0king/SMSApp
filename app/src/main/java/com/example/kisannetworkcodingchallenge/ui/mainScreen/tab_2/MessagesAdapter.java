package com.example.kisannetworkcodingchallenge.ui.mainScreen.tab_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kisannetworkcodingchallenge.R;
import com.example.kisannetworkcodingchallenge.data.model.Contact;
import com.example.kisannetworkcodingchallenge.data.model.Message;
import com.example.kisannetworkcodingchallenge.ui.Router;
import com.example.kisannetworkcodingchallenge.ui.mainScreen.tab_1.ContactsAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageHolder> {
    private List<Message> mList = new ArrayList<>();
    private SimpleDateFormat f = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.getDefault());

    protected MessagesAdapter(){}

    protected MessagesAdapter(List<Message> list){
        mList = list;
    }

    protected void updateList(List<Message> newList){
        final ListDiffUtilCallback diffCallback = new ListDiffUtilCallback(mList, newList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        mList.clear();
        mList.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }


    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    protected class MessageHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvOtp;
        private TextView tvTime;
        MessageHolder(View view){
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvOtp = view.findViewById(R.id.tvOtp);
            tvTime = view.findViewById(R.id.tvTime);
        }

        void bind(Message m){
            tvName.setText(m.getPersonName());
            tvOtp.setText(m.getOtp());
            tvTime.setText(getFormattedDate(m.getCreatedOn()));
        }
    }

    private String getFormattedDate(long time){
        return f.format(time);
    }
}
