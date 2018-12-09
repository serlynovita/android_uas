package com.serly.uas_mobile.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetOngkosKirim {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<OngkosKirim> listDataOngkosKirim;
    @SerializedName("message")
    String message;

    public GetOngkosKirim(String status, List<OngkosKirim> listDataOngkosKirim, String message) {
        this.status = status;
        this.listDataOngkosKirim = listDataOngkosKirim;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OngkosKirim> getListDataOngkosKirim() {
        return listDataOngkosKirim;
    }

    public void setListDataOngkosKirim(List<OngkosKirim> listDataOngkosKirim) {
        this.listDataOngkosKirim = listDataOngkosKirim;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
