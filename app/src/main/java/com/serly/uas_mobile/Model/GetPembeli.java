package com.serly.uas_mobile.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
public class GetPembeli {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Pembeli> listDataPembeli;
    @SerializedName("message")
    String message;

    public GetPembeli(String status, List<Pembeli> listDataPembeli, String message) {
        this.status = status;
        this.listDataPembeli = listDataPembeli;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Pembeli> getListDataPembeli() {
        return listDataPembeli;
    }

    public void setListDataPembeli(List<Pembeli> listDataPembeli) {
        this.listDataPembeli = listDataPembeli;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}