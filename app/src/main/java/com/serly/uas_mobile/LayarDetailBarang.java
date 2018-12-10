package com.serly.uas_mobile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.serly.uas_mobile.Rest.ApiClient;
import com.serly.uas_mobile.Rest.ApiInterfaceBarang;

public class LayarDetailBarang extends AppCompatActivity {

    Context mContext;
    EditText edtIdBarang, edtNamaBarang,edtWarnaBarang, edtkategoriBarang,
            edtBeratBarang, edtDeskripsi, edtHarga, edtStok ;
    ImageView imgFoto;
    Button btInsert, btUpdate, btDelete, btBack;
    TextView tvMessage;
    ApiInterfaceBarang mApiInterfaceBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_detail_barang);

        mContext = getApplicationContext();
        edtIdBarang =(EditText) findViewById(R.id.edtIdBarang);
        edtNamaBarang =(EditText) findViewById(R.id.edtNamaBarang);
        edtWarnaBarang =(EditText) findViewById(R.id.edtWarnaBarang);
        edtkategoriBarang =(EditText) findViewById(R.id.edtKategoriBarang);
        edtBeratBarang =(EditText) findViewById(R.id.edtBeratBarang);
        edtDeskripsi =(EditText) findViewById(R.id.edtDeskripsi);
        edtHarga =(EditText) findViewById(R.id.edtHarga);
        edtStok =(EditText) findViewById(R.id.edtStok);
        imgFoto =(ImageView) findViewById(R.id.imgFoto);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);

        btInsert = (Button) findViewById(R.id.btInsert2);
        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtIdBarang.setText(mIntent.getStringExtra("id_barang"));
        edtNamaBarang.setText(mIntent.getStringExtra("nama_barang"));
        edtWarnaBarang.setText(mIntent.getStringExtra("warna_barang"));
        edtkategoriBarang.setText(mIntent.getStringExtra("kategori_barang"));
        edtBeratBarang.setText(mIntent.getStringExtra("berat_barang"));
        edtDeskripsi.setText(mIntent.getStringExtra("deskripsi"));
        edtHarga.setText(mIntent.getStringExtra("harga"));
        edtStok.setText(mIntent.getStringExtra("stok"));
        Glide.with(mContext).load(ApiClient.BASE_URL+"uploads/"+mIntent.getStringExtra("foto")).into(imgFoto);

        mApiInterfaceBarang = ApiClient.getClient().create(ApiInterfaceBarang.class);

        btBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(),ActivityBarang.class);
                startActivity(mIntent);
            }
        });
    }
}
