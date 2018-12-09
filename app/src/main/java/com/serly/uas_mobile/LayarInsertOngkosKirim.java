package com.serly.uas_mobile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.serly.uas_mobile.Model.GetOngkosKirim;
import com.serly.uas_mobile.Rest.ApiClient;
import com.serly.uas_mobile.Rest.ApiInterfaceOngkosKirim;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarInsertOngkosKirim extends AppCompatActivity {

    Context mContext;
    Button btInsert, btBack;
    EditText edtIdOngkir, edtKota,edtHarga;
    TextView tvAddMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_insert_ongkos_kirim);

        mContext = getApplicationContext();

        edtIdOngkir =(EditText) findViewById(R.id.edtIdOngkir);
        edtKota =(EditText) findViewById(R.id.edtKota);
        edtHarga =(EditText) findViewById(R.id.edtHarga);
        tvAddMessage =(TextView) findViewById(R.id.tvMessage2);

        btInsert = (Button) findViewById(R.id.btInsert2);
        btBack = (Button) findViewById(R.id.btBack);

        final ApiInterfaceOngkosKirim mApiInterfaceOngkosKirim = ApiClient.getClient().create(ApiInterfaceOngkosKirim.class);

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterfaceOngkosKirim mApiInterfaceOngkosKirim = ApiClient.getClient().create(ApiInterfaceOngkosKirim.class);

                RequestBody reqIdOngkir = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        "");
                RequestBody reqKota = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtKota.getText().toString().isEmpty())?"":edtKota.getText().toString());
                RequestBody reqHarga = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtHarga.getText().toString().isEmpty())?"":edtHarga.getText().toString());
                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),"insert");
                Call<GetOngkosKirim> mOngkosKirimCall = mApiInterfaceOngkosKirim.postOngkosKirim(reqIdOngkir,reqKota,reqHarga,reqAction);

                mOngkosKirimCall.enqueue(new Callback<GetOngkosKirim>() {
                    @Override
                    public void onResponse(Call<GetOngkosKirim> call, Response<GetOngkosKirim> response) {

                        tvAddMessage.setText("Retrofit Insert = "+ response.body().getStatus() + "\n" );

                    }

                    @Override
                    public void onFailure(Call<GetOngkosKirim> call, Throwable t) {
                        tvAddMessage.setText("Retrofit Insert Failure \n Status = " + t.getMessage());
                    }
                });
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,ActivityOngkosKirim.class);
                startActivity(intent);
            }
        });
    }
}
