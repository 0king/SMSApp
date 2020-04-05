package com.example.kisannetworkcodingchallenge.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Contact implements Parcelable {

    @SerializedName("_id")
    private String _id;

    @SerializedName("name")
    private String fullName;

    @SerializedName("phone")
    private String phoneNumber;

    public Contact(String _id, String fullName, String phoneNumber) {
        this._id = _id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(_id);
        out.writeString(fullName);
        out.writeString(phoneNumber);
    }

    private Contact(Parcel in) {
        _id = in.readString();
        fullName = in.readString();
        phoneNumber = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Contact> CREATOR
            = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }
        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
