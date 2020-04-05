package com.example.kisannetworkcodingchallenge.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.kisannetworkcodingchallenge.data.model.Message;
import com.example.kisannetworkcodingchallenge.ui.mainScreen.tab_2.MessagesFragment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Message.class}, version = 1, exportSchema = false)
public abstract class MessageRoomDatabase extends RoomDatabase {

    public abstract MessageDao messageDao();

    private static volatile MessageRoomDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MessageRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MessagesFragment.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MessageRoomDatabase.class, "message_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}