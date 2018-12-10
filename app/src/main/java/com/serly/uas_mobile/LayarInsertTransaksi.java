package com.serly.uas_mobile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.serly.uas_mobile.Model.GetPembeli;
import com.serly.uas_mobile.Model.GetTransaksi;
import com.serly.uas_mobile.Rest.ApiClient;
import com.serly.uas_mobile.Rest.ApiInterfacePembeli;
import com.serly.uas_mobile.Rest.ApiInterfaceTransaksi;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarInsertTransaksi extends AppCompatActivity {

    Context mContext;
    Button btInsert, btBack;
    EditText edtIdTransaksi, edtIdPembeli,edtIdOngkir, edtTotalHarga, edtTglBeli;
    Spinner spinnerStatus;
    TextView tvAddMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_insert_transaksi);

        mContext = getApplicationContext();

        edtIdTransaksi =(EditText) findViewById(R.id.edtIdTransaksi);
        edtIdPembeli =(EditText) findViewById(R.id.edtIdPembeli);
        edtIdOngkir =(EditText) findViewById(R.id.edtIdOngkir);
        edtTotalHarga =(EditText) findViewById(R.id.edtTotalHarga);
        edtTglBeli =(EditText) findViewById(R.id.edtTglBeli);
        spinnerStatus =(Spinner) findViewById(R.id.spinnerStatus);
        tvAddMessage =(TextView) findViewById(R.id.tvMessage2);

        btInsert = (Button) findViewById(R.id.btInsert2);
        btBack = (Button) findViewById(R.id.btBack);

        final ApiInterfaceTransaksi mApiInterfaceTransaksi = ApiClient.getClient().create(ApiInterfaceTransaksi.class);

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterfaceTransaksi mApiInterface = ApiClient.getClient().create(ApiInterfaceTransaksi.class);

                RequestBody reqIdTransaksi = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        "");
                RequestBody reqIdPembeli = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtIdPembeli.getText().toString().isEmpty())?"":edtIdPembeli.getText().toString());
                RequestBody reqIdOngkir = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtIdOngkir.getText().toString().isEmpty())?"":edtIdOngkir.getText().toString());
                RequestBody reqTotalHarga = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtTotalHarga.getText().toString().isEmpty())?"":edtTotalHarga.getText().toString());
                RequestBody reqTglBeli = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtTglBeli.getText().toString().isEmpty())?"":edtTglBeli.getText().toString());
                RequestBody reqStatus = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (spinnerStatus.getSelectedItem().toString()));
                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),"insert");

                Call<GetTransaksi> mTransaksiCall = mApiInterface.postTransaksi(reqIdTransaksi,reqIdPembeli,reqIdOngkir,reqTotalHarga,reqTglBeli,reqStatus,reqAction);

                mTransaksiCall.enqueue(new Callback<GetTransaksi>() {
                    @Override
                    public void onResponse(Call<GetTransaksi> call, Response<GetTransaksi> response) {

                        tvAddMessage.setText("Retrofit Insert = "+ response.body().getStatus() + "\n" );

                    }

                    @Override
                    public void onFailure(Call<GetTransaksi> call, Throwable t) {
                        tvAddMessage.setText("Retrofit Insert Failure \n Status = " + t.getMessage());
                    }
                });
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,ActivityTransaksi.class);
                startActivity(intent);
            }
        });
    }
}

