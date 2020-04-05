package com.example.kisannetworkcodingchallenge.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.kisannetworkcodingchallenge.data.model.Message;
import com.example.kisannetworkcodingchallenge.data.model.StateLiveData;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM messages ORDER BY created_on DESC")
    LiveData<List<Message>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Message message);
}
