package com.serly.uas_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.serly.uas_mobile.Model.GetOngkosKirim;
import com.serly.uas_mobile.Model.GetPembeli;
import com.serly.uas_mobile.Rest.ApiClient;
import com.serly.uas_mobile.Rest.ApiInterfaceOngkosKirim;
import com.serly.uas_mobile.Rest.ApiInterfacePembeli;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarEditOngkosKirim extends AppCompatActivity {

    EditText edtIdOngkir, edtKota,edtHarga;
    Button btInsert, btUpdate, btDelete, btBack;
    TextView tvMessage;
    ApiInterfaceOngkosKirim mApiInterfaceOngkosKirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_edit_ongkos_kirim);

        edtIdOngkir =(EditText) findViewById(R.id.edtIdOngkir);
        edtKota =(EditText) findViewById(R.id.edtKota);
        edtHarga =(EditText) findViewById(R.id.edtHarga);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);

        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtIdOngkir.setText(mIntent.getStringExtra("id_ongkir"));
        edtKota.setText(mIntent.getStringExtra("kota"));
        edtHarga.setText(mIntent.getStringExtra("harga"));

        mApiInterfaceOngkosKirim = ApiClient.getClient().create(ApiInterfaceOngkosKirim.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestBody reqIdOngkir = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtIdOngkir.getText().toString().isEmpty())?"":edtIdOngkir.getText().toString());
                RequestBody reqKota = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtKota.getText().toString().isEmpty())?"":edtKota.getText().toString());
                RequestBody reqHarga = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtHarga.getText().toString().isEmpty())?"":edtHarga.getText().toString());
                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),"update");

                Call<GetOngkosKirim> callUpdate = mApiInterfaceOngkosKirim.putOngkosKirim(reqIdOngkir,reqKota,reqHarga,reqAction);

                callUpdate.enqueue(new Callback<GetOngkosKirim>() {
                    @Override
                    public void onResponse(Call<GetOngkosKirim> call, Response<GetOngkosKirim> response) {
                        if(response.body().getStatus().equals("failed")){
                            tvMessage.setText("Retrofit Update \n Status = " +
                                    response.body().getStatus() );
                        }
                        else {
                            String detail = "\n" + "Data isi dengan lengkap" + "\n" ;
                            tvMessage.setText("Retrofit Update = " + response.body().getStatus() + "\n" );
                        }
                    }

                    @Override
                    public void onFailure(Call<GetOngkosKirim> call, Throwable t) {
                        tvMessage.setText("Retrofit Update \n Status = " + t.getMessage());
                    }
                });
            }
        });

        btBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(),ActivityOngkosKirim.class);
                startActivity(mIntent);
            }
        });
    }
}

