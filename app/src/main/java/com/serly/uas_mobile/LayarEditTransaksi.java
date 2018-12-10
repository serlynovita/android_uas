package com.serly.uas_mobile;

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

public class LayarEditTransaksi extends AppCompatActivity {

    EditText edtIdTransaksi, edtIdPembeli,edtIdOngkir, edtTotalHarga, edtTglBeli;
    Spinner spinnerStatus;
    Button btInsert, btUpdate, btDelete, btBack;
    TextView tvMessage;
    ApiInterfaceTransaksi mApiInterfaceTransaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_edit_transaksi);

        edtIdTransaksi =(EditText) findViewById(R.id.edtIdTransaksi);
        edtIdPembeli =(EditText) findViewById(R.id.edtIdPembeli);
        edtIdOngkir =(EditText) findViewById(R.id.edtIdOngkir);
        edtTotalHarga =(EditText) findViewById(R.id.edtTotalHarga);
        edtTglBeli =(EditText) findViewById(R.id.edtTglBeli);
        spinnerStatus =(Spinner) findViewById(R.id.spinnerStatus);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);

        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtIdTransaksi.setText(mIntent.getStringExtra("id_transaksi"));
        edtIdPembeli.setText(mIntent.getStringExtra("id_pembeli"));
        edtIdOngkir.setText(mIntent.getStringExtra("id_ongkir"));
        edtTotalHarga.setText(mIntent.getStringExtra("total_harga"));
        edtTglBeli.setText(mIntent.getStringExtra("tgl_beli"));
        if (mIntent.getStringExtra("status") == "Terkirim"){
            spinnerStatus.setSelection(0);
        } else {
            spinnerStatus.setSelection(1);
        }

        mApiInterfaceTransaksi = ApiClient.getClient().create(ApiInterfaceTransaksi.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestBody reqIdTransaksi = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtIdTransaksi.getText().toString().isEmpty())?"":edtIdTransaksi.getText().toString());
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
                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),"update");

                Call<GetTransaksi> callUpdate = mApiInterfaceTransaksi.putTransaksi(reqIdTransaksi,reqIdPembeli,reqIdOngkir,reqTotalHarga,reqTglBeli,reqStatus,reqAction);

                callUpdate.enqueue(new Callback<GetTransaksi>() {
                    @Override
                    public void onResponse(Call<GetTransaksi> call, Response<GetTransaksi> response) {
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
                    public void onFailure(Call<GetTransaksi> call, Throwable t) {
                        tvMessage.setText("Retrofit Update \n Status = " + t.getMessage());
                    }
                });
            }
        });

        btBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(),ActivityTransaksi.class);
                startActivity(mIntent);
            }
        });
    }
}


