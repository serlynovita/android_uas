package com.serly.uas_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.serly.uas_mobile.Rest.ApiClient;
import com.serly.uas_mobile.Rest.ApiInterfaceTransaksi;

public class LayarDetailTransaksi extends AppCompatActivity {

    EditText edtIdTransaksi, edtIdPembeli,edtIdOngkir, edtTotalHarga, edtTglBeli, edtStatus;
    Button btInsert, btUpdate, btDelete, btBack;
    TextView tvMessage;
    ApiInterfaceTransaksi mApiInterfaceTransaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_detail_transaksi);

        edtIdTransaksi =(EditText) findViewById(R.id.edtIdTransaksi);
        edtIdPembeli =(EditText) findViewById(R.id.edtIdPembeli);
        edtIdOngkir =(EditText) findViewById(R.id.edtIdOngkir);
        edtTotalHarga =(EditText) findViewById(R.id.edtTotalHarga);
        edtTglBeli =(EditText) findViewById(R.id.edtTglBeli);
        edtStatus =(EditText) findViewById(R.id.edtStatus);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);

        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtIdTransaksi.setText(mIntent.getStringExtra("id_transaksi"));
        edtIdPembeli.setText(mIntent.getStringExtra("id_pembeli"));
        edtIdOngkir.setText(mIntent.getStringExtra("id_ongkir"));
        edtTotalHarga.setText(mIntent.getStringExtra("total_harga"));
        edtTglBeli.setText(mIntent.getStringExtra("Tgl_beli"));
        edtStatus.setText(mIntent.getStringExtra("status"));

        mApiInterfaceTransaksi = ApiClient.getClient().create(ApiInterfaceTransaksi.class);

        btBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(),ActivityTransaksi.class);
                startActivity(mIntent);
            }
        });
    }
}

