package com.serly.uas_mobile.Model;

import com.google.gson.annotations.SerializedName;

public class OngkosKirim {
    @SerializedName("id_ongkir")
    private String idOngkir;
    @SerializedName("kota")
    private String kota;
    @SerializedName("harga")
    private String harga;
    private String action;

    public OngkosKirim(String idOngkir, String kota, String harga, String action) {
        this.idOngkir = idOngkir;
        this.kota = kota;
        this.harga = harga;
        this.action = action;
    }

    public String getIdOngkir() {
        return idOngkir;
    }

    public void setIdOngkir(String idOngkir) {
        this.idOngkir = idOngkir;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

