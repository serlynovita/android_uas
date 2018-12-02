package com.serly.uas_mobile.Rest;

import com.serly.uas_mobile.Model.GetPembeli;
import com.serly.uas_mobile.Model.PostPutDelPembeli;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface ApiInterfacePembeli {
    @GET("pembeli/pembeli")
    Call<GetPembeli> getPembeli();

    @FormUrlEncoded
    @POST("pembeli/pembeli")
    Call<PostPutDelPembeli> postPembeli(
            @Field("id_pembeli") String id_pembeli, @Field("nama_pembeli") String nama_pembeli,
            @Field("alamat") String alamat, @Field("telpn") String telpn,
            @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @PUT("pembeli/pembeli")
    Call<PostPutDelPembeli> putPembeli(
            @Field("id_pembeli") String id_pembeli, @Field("nama_pembeli") String nama_pembeli,
            @Field("alamat") String alamat, @Field("telpn") String telpn,
            @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "pembeli/pembeli", hasBody = true)
    Call<PostPutDelPembeli> deletePembeli(@Field("id_pembeli") String idPembeli);

//    @GET("pembeli/all")
//    Call<GetPembeli> getPembeli();

    @Multipart
    @POST("pembeli/pembeli")
    Call<GetPembeli> postPembeli(
            @Part("id_pembeli") RequestBody id_pembeli,
            @Part("nama_pembeli") RequestBody nama_pembeli,
            @Part("alamat") RequestBody alamat,
            @Part("telp") RequestBody telpn,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("pembeli/pembeli")
    Call<GetPembeli> putPembeli(
            @Part("id_pembeli") RequestBody id_pembeli,
            @Part("nama_pembeli") RequestBody nama_pembeli,
            @Part("alamat") RequestBody alamat,
            @Part("telp") RequestBody telpn,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("pembeli/pembeli")
    Call<GetPembeli> deletePembeli(
            @Part("id_pembeli") RequestBody id_pembeli,
            @Part("action") RequestBody action
    );
}


