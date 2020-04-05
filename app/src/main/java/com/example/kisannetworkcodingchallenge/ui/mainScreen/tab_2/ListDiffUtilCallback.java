package com.example.kisannetworkcodingchallenge.ui.mainScreen.tab_2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.kisannetworkcodingchallenge.data.model.Message;

import java.util.ArrayList;
import java.util.List;

public class ListDiffUtilCallback extends DiffUtil.Callback {
    private List<Message> newList;
    private List<Message> oldList;

    public ListDiffUtilCallback(List<Message> newList, List<Message> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition).getCreatedOn() == oldList.get(oldItemPosition).getCreatedOn();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition).getOtp().equals(oldList.get(oldItemPosition).getOtp());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {

        Message newModel = newList.get(newItemPosition);
        Message oldModel = oldList.get(oldItemPosition);

        Bundle diff = new Bundle();

        if (!newModel.getOtp().equals(oldModel.getOtp())) {
            diff.putString("otp", newModel.getOtp());
            diff.putString("personName", newModel.getPersonName());
        }
        if (diff.size() == 0) {
            return null;
        }
        return diff;
    }
}
