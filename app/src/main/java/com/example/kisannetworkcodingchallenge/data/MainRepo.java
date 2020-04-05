package com.example.kisannetworkcodingchallenge.data;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.kisannetworkcodingchallenge.R;
import com.example.kisannetworkcodingchallenge.data.local.MessageDao;
import com.example.kisannetworkcodingchallenge.data.local.MessageRoomDatabase;
import com.example.kisannetworkcodingchallenge.data.model.Contact;
import com.example.kisannetworkcodingchallenge.data.model.DataStatus;
import com.example.kisannetworkcodingchallenge.data.model.Message;
import com.example.kisannetworkcodingchallenge.data.model.StateLiveData;
import com.example.kisannetworkcodingchallenge.data.remote.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public enum MainRepo implements IRepo{
    //singleton
    INSTANCE;

    private LiveData<List<Message>> mAllMessages = null;
    private MessageDao mMessageDao = null;
    private Retrofit retrofit = null;
    private Api mApi;

    MainRepo(){
        initRetrofit();
    }

    public Single<JsonElement> sendSMS(String to, String message){
        return mApi.sendSMS(to, message)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private void initRetrofit(){
        if (retrofit == null){
            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Api.SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        mApi = retrofit.create(Api.class);
    }

    //getAllMessages
    @Override
    public LiveData<List<Message>> getAllMessages(Application a){
        if (mAllMessages == null){
            initDb(a);
        }
        return mAllMessages;
    }

    //saveMessage
    @Override
    public void insertMessage(Application a, Message m){
        if (mMessageDao==null){
            initDb(a);
        }
        MessageRoomDatabase.databaseWriteExecutor.execute(()->{
            mMessageDao.insert(m);
        });
    }

    private void initDb(Application a){
        MessageRoomDatabase db = MessageRoomDatabase.getDatabase(a);
        mMessageDao = db.messageDao();
        mAllMessages = mMessageDao.getAll();
    }

    @Override
    public Single<List<Contact>> getAllContacts(Context context){
        return Single.create(new SingleOnSubscribe<List<Contact>>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<List<Contact>> emitter) throws Throwable {
                if (!emitter.isDisposed()){
                    List<Contact> list = getContactsFromFile(context);
                    if (list==null) emitter.onError(new Exception("File read error"));
                    else emitter.onSuccess(list);
                }
            }}).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private List<Contact> getContactsFromFile(Context context) {
        List<Contact> list = null;
        Resources r = context.getResources();
        InputStream in = r.openRawResource(R.raw.contacts);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            /*String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }*/
        } catch (IOException e) {
            Log.e("durga", "IOException", e);
        } finally {
            try {
                in.close();
                String jsonString = writer.toString();
                Contact[] contacts = new Gson().fromJson(jsonString, Contact[].class);
                list = Arrays.asList(contacts);
            } catch (IOException e) {
                Log.e("durga", "IOException 1", e);
            }
            finally {
                return list;
            }
        }
    }

}
