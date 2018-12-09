package com.serly.uas_mobile.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetBarang {
    @SerializedName("status")
    private String status;
    @SerializedName ("result")
    private List<Barang> result = new ArrayList<Barang>();
    @SerializedName ("message")
    private String message;

    public GetBarang() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Barang> getResult() {
        return result;
    }

    public void setResult(List<Barang> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}