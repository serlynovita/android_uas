package com.serly.uas_mobile.Rest;

import com.serly.uas_mobile.Model.GetOngkosKirim;
import com.serly.uas_mobile.Model.GetPembeli;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterfaceOngkosKirim {
    @GET("OngkosKirim/ongkoskirim")
    Call<GetOngkosKirim> getOngkosKirim();

    @Multipart
    @POST("OngkosKirim/ongkoskirim")
    Call<GetOngkosKirim> postOngkosKirim(
            @Part("id_ongkir") RequestBody id_ongkir,
            @Part("kota") RequestBody kota,
            @Part("harga") RequestBody harga,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("OngkosKirim/ongkoskirim")
    Call<GetOngkosKirim> putOngkosKirim(
            @Part("id_ongkir") RequestBody id_ongkir,
            @Part("kota") RequestBody kota,
            @Part("harga") RequestBody harga,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("OngkosKirim/ongkoskirim")
    Call<GetOngkosKirim> deleteOngkosKirim(
            @Part("id_ongkir") RequestBody id_ongkir,
            @Part("action") RequestBody action
    );
}



