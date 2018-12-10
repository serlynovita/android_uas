package com.serly.user.Rest;

import com.serly.user.Model.GetBarang;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterfaceBarang {
    @GET("Barang/barang")
    Call<GetBarang> getBarang();

    @Multipart
    @POST("Barang/barang")
    Call<GetBarang> postBarang(
            @Part MultipartBody.Part file,
            @Part("id_barang") RequestBody id_barang,
            @Part("nama_barang") RequestBody nama_barang,
            @Part("warna_barang") RequestBody warna_barang,
            @Part("kategori_barang") RequestBody kategori_barang,
            @Part("berat_barang") RequestBody berat_barang,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("harga") RequestBody harga,
            @Part("stok") RequestBody stok,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("Barang/barang")
    Call<GetBarang> putBarang(
            @Part MultipartBody.Part file,
            @Part("id_barang") RequestBody id_barang,
            @Part("nama_barang") RequestBody nama_barang,
            @Part("warna_barang") RequestBody warna_barang,
            @Part("kategori_barang") RequestBody kategori_barang,
            @Part("berat_barang") RequestBody berat_barang,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("harga") RequestBody harga,
            @Part("stok") RequestBody stok,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("Barang/barang")
    Call<GetBarang> deleteBarang(
            @Part("id_barang") RequestBody id_barang,
            @Part("action") RequestBody action);
}
