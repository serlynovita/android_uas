package com.serly.uas_mobile.Model;

import com.google.gson.annotations.SerializedName;
import com.serly.uas_mobile.Model.Pembeli;

public class PostPutDelPembeli {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Pembeli mPembeli;
    @SerializedName("message")
    String message;

    public PostPutDelPembeli(String status, Pembeli mPembeli, String message) {
        this.status = status;
        this.mPembeli = mPembeli;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pembeli getmPembeli() {
        return mPembeli;
    }

    public void setmPembeli(Pembeli mPembeli) {
        this.mPembeli = mPembeli;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
