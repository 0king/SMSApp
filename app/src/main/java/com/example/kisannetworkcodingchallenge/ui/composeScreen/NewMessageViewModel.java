package com.example.kisannetworkcodingchallenge.ui.composeScreen;

import android.app.Application;
import android.util.Base64;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.kisannetworkcodingchallenge.data.MainRepo;
import com.example.kisannetworkcodingchallenge.data.model.DataStatus;
import com.example.kisannetworkcodingchallenge.data.model.Message;
import com.example.kisannetworkcodingchallenge.data.model.StateLiveData;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class NewMessageViewModel extends AndroidViewModel {

    private MutableLiveData<DataStatus> mResponse = new MutableLiveData<>();
    private MutableLiveData<String> mOTPLive = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    public NewMessageViewModel(Application a){
        super(a);
        generateOtp();
    }

    public LiveData<String> getOtp(){
        return mOTPLive;
    }

    public LiveData<DataStatus> getSmsServerResponse(){
        return mResponse;
    }

    public void onHitSend(Message message, String to, String msg){
        Disposable d = MainRepo.INSTANCE.sendSMS(to, msg)
                .subscribe(response -> {
                            mResponse.setValue(DataStatus.SUCCESS);
                            //save locally
                            MainRepo.INSTANCE.insertMessage(getApplication(), message);
                        },
                        err -> {
                            mResponse.setValue(DataStatus.ERROR);
                        });
        disposable.add(d);
    }

    private void generateOtp(){
        mOTPLive.setValue(getRandomNumberString());
    }

    private String getRandomNumberString() {
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        return String.valueOf(n);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }

}
