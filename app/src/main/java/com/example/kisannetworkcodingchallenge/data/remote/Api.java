package com.example.kisannetworkcodingchallenge.data.remote;

import com.example.kisannetworkcodingchallenge.data.model.DataStatus;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.Map;

import io.reactivex.rxjava3.core.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    String SERVER_URL = "https://twilio-sms-server-1.herokuapp.com/";

    @FormUrlEncoded
    @POST("/sms")
    Single<JsonElement> sendSMS(@Field("To") String to, @Field("Body") String body);

}
