package com.serly.uas_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.serly.uas_mobile.Rest.ApiClient;
import com.serly.uas_mobile.Rest.ApiInterfaceOngkosKirim;

public class LayarDetailOngkosKirim extends AppCompatActivity {

    EditText edtIdOngkir, edtKota,edtHarga;
    Button btInsert, btUpdate, btDelete, btBack;
    TextView tvMessage;
    ApiInterfaceOngkosKirim mApiInterfaceOngkosKirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_detail_ongkos_kirim);

        edtIdOngkir =(EditText) findViewById(R.id.edtIdOngkir);
        edtKota =(EditText) findViewById(R.id.edtKota);
        edtHarga =(EditText) findViewById(R.id.edtHarga);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);

        btInsert = (Button) findViewById(R.id.btInsert2);
        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtIdOngkir.setText(mIntent.getStringExtra("id_ongkir"));
        edtKota.setText(mIntent.getStringExtra("kota"));
        edtHarga.setText(mIntent.getStringExtra("harga"));

        mApiInterfaceOngkosKirim = ApiClient.getClient().create(ApiInterfaceOngkosKirim.class);

        btBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(),ActivityOngkosKirim.class);
                startActivity(mIntent);
            }
        });
    }
}
