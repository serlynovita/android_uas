package com.serly.user.Model;

import com.google.gson.annotations.SerializedName;

public class Transaksi {
    @SerializedName("id_transaksi")
    private String idTransaksi;
    @SerializedName("id_pembeli")
    private String idPembeli;
    @SerializedName("id_ongkir")
    private String idOngkir;
    @SerializedName("total_harga")
    private String totalHarga;
    @SerializedName("tgl_beli")
    private String tglBeli;
    @SerializedName("status")
    private String status;
    private String action;

    public Transaksi(String idTransaksi, String idPembeli, String idOngkir, String totalHarga, String tglBeli, String status, String action) {
        this.idTransaksi = idTransaksi;
        this.idPembeli = idPembeli;
        this.idOngkir = idOngkir;
        this.totalHarga = totalHarga;
        this.tglBeli = tglBeli;
        this.status = status;
        this.action = action;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(String idPembeli) {
        this.idPembeli = idPembeli;
    }

    public String getIdOngkir() {
        return idOngkir;
    }

    public void setIdOngkir(String idOngkir) {
        this.idOngkir = idOngkir;
    }

    public String getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String getTglBeli() {
        return tglBeli;
    }

    public void setTglBeli(String tglBeli) {
        this.tglBeli = tglBeli;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}