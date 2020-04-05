package com.example.kisannetworkcodingchallenge.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "messages")
public class Message {

    @PrimaryKey
    @ColumnInfo(name = "created_on")
    private long createdOn;

    @ColumnInfo(name = "otp")
    private String otp;

    @ColumnInfo(name = "person_name")
    private String personName;

    public Message(long createdOn, String otp, String personName) {
        this.createdOn = createdOn;
        this.otp = otp;
        this.personName = personName;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public String getOtp() {
        return otp;
    }

    public String getPersonName() {
        return personName;
    }
}
