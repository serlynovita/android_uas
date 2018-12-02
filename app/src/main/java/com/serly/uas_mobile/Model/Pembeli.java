package com.serly.uas_mobile.Model;

import com.google.gson.annotations.SerializedName;

public class Pembeli {
    @SerializedName("id_pembeli")
    private String idPembeli;
    @SerializedName("nama_pembeli")
    private String namaPembeli;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("telpn")
    private String telpn;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    private String action;

    public Pembeli(String idPembeli, String namaPembeli, String alamat, String telpn, String email, String password, String action) {
        this.idPembeli = idPembeli;
        this.namaPembeli = namaPembeli;
        this.alamat = alamat;
        this.telpn = telpn;
        this.email = email;
        this.password = password;
        this.action = action;
    }

    public String getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(String idPembeli) {
        this.idPembeli = idPembeli;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelpn() {
        return telpn;
    }

    public void setTelpn(String telpn) {
        this.telpn = telpn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
