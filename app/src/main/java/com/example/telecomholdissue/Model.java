package com.example.telecomholdissue;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class Model {

    private static Model instance;

    private MyTelecomConnection connection;
    private MutableLiveData<Boolean> hold = new MutableLiveData<>();

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public void setConnection(MyTelecomConnection connection) {
        this.connection = connection;
    }

    public MyTelecomConnection getConnection() {
        return connection;
    }

    public void setHold(boolean hold) {
        this.hold.postValue(hold);
    }

    public LiveData<Boolean> getHold() {
        return hold;
    }
}
