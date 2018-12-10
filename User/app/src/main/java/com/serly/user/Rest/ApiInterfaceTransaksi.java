package com.serly.user.Rest;

import com.serly.user.Model.GetTransaksi;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterfaceTransaksi {
    @GET("Transaksi/transaksi")
    Call<GetTransaksi> getTransaksi();

//    @FormUrlEncoded
//    @POST("pembeli/pembeli")
//    Call<PostPutDelPembeli> postPembeli(
//            @Field("id_pembeli") String id_pembeli, @Field("nama_pembeli") String nama_pembeli,
//            @Field("alamat") String alamat, @Field("telpn") String telpn,
//            @Field("email") String email, @Field("password") String password);
//
//    @FormUrlEncoded
//    @PUT("pembeli/pembeli")
//    Call<PostPutDelPembeli> putPembeli(
//            @Field("id_pembeli") String id_pembeli, @Field("nama_pembeli") String nama_pembeli,
//            @Field("alamat") String alamat, @Field("telpn") String telpn,
//            @Field("email") String email, @Field("password") String password);
//
//    @FormUrlEncoded
//    @HTTP(method = "DELETE", path = "pembeli/pembeli", hasBody = true)
//    Call<PostPutDelPembeli> deletePembeli(@Field("id_pembeli") String idPembeli);

//    @GET("pembeli/all")
//    Call<GetPembeli> getPembeli();

    @Multipart
    @POST("Transaksi/transaksi")
    Call<GetTransaksi> postTransaksi(
            @Part("id_transaksi") RequestBody id_transaksi,
            @Part("id_pembeli") RequestBody id_pembeli,
            @Part("id_ongkir") RequestBody id_ongkir,
            @Part("total_harga") RequestBody total_harga,
            @Part("tgl_beli") RequestBody tgl_beli,
            @Part("status") RequestBody status,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("Transaksi/transaksi")
    Call<GetTransaksi> putTransaksi(
            @Part("id_transaksi") RequestBody id_transaksi,
            @Part("id_pembeli") RequestBody id_pembeli,
            @Part("id_ongkir") RequestBody id_ongkir,
            @Part("total_harga") RequestBody total_harga,
            @Part("tgl_beli") RequestBody tgl_beli,
            @Part("status") RequestBody status,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("Transaksi/transaksi")
    Call<GetTransaksi> deleteTransaksi(
            @Part("id_transaksi") RequestBody id_transaksi,
            @Part("action") RequestBody action
    );
}



