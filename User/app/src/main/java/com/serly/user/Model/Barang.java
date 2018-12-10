package com.serly.user.Model;

import com.google.gson.annotations.SerializedName;

public class Barang {
    @SerializedName("id_barang")
    private String idBarang;
    @SerializedName("nama_barang")
    private String namaBarang;
    @SerializedName("warna_barang")
    private String warnaBarang;
    @SerializedName("kategori_barang")
    private String kategoriBarang;
    @SerializedName("berat_barang")
    private String beratBarang;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("harga")
    private String harga;
    @SerializedName("stok")
    private String stok;
    @SerializedName("foto")
    private String foto;
    private String action;

    public Barang(String idBarang, String namaBarang, String warnaBarang,
                  String kategoriBarang, String beratBarang, String deskripsi,
                  String harga, String stok, String foto, String action) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.warnaBarang = warnaBarang;
        this.kategoriBarang = kategoriBarang;
        this.beratBarang = beratBarang;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.stok = stok;
        this.foto = foto;
        this.action = action;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getWarnaBarang() {
        return warnaBarang;
    }

    public void setWarnaBarang(String warnaBarang) {
        this.warnaBarang = warnaBarang;
    }

    public String getKategoriBarang() {
        return kategoriBarang;
    }

    public void setKategoriBarang(String kategoriBarang) {
        this.kategoriBarang = kategoriBarang;
    }

    public String getBeratBarang() {
        return beratBarang;
    }

    public void setBeratBarang(String beratBarang) {
        this.beratBarang = beratBarang;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
