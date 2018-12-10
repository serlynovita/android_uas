package com.serly.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.serly.user.Model.GetTransaksi;
import com.serly.user.Rest.ApiClient;
import com.serly.user.Rest.ApiInterfaceBarang;
import com.serly.user.Rest.ApiInterfaceTransaksi;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarDetailBarang extends AppCompatActivity {

    Context mContext;
    EditText edtIdBarang, edtNamaBarang,edtWarnaBarang, edtkategoriBarang,
            edtBeratBarang, edtDeskripsi, edtHarga, edtStok ;
    ImageView imgFoto;
    Button btInsert, btBeli,btUpdate, btDelete, btBack;
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

        btBeli = (Button) findViewById(R.id.btBeli);
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
                Intent mIntent = new Intent(getApplicationContext(), ActivityBarang.class);
                startActivity(mIntent);
            }
        });

        btBeli.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String timesatamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                ApiInterfaceTransaksi mApiInterface = ApiClient.getClient().create(ApiInterfaceTransaksi.class);

                Log.d("Transaksi", "12, 1, "+edtHarga.getText().toString()+", "+timesatamp+", menunggu");
                RequestBody reqIdTransaksi = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        "");
                RequestBody reqIdPembeli = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        ("12"));
                RequestBody reqIdOngkir = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        ("1"));
                RequestBody reqTotalHarga = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtHarga.getText().toString().isEmpty())?"":edtHarga.getText().toString());
                RequestBody reqTglBeli = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (timesatamp));
                RequestBody reqStatus = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        ("menunggu"));
                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),"insert");

                Call<GetTransaksi> mTransaksiCall = mApiInterface.postTransaksi(reqIdTransaksi,reqIdPembeli,reqIdOngkir,reqTotalHarga,reqTglBeli,reqStatus,reqAction);

                mTransaksiCall.enqueue(new Callback<GetTransaksi>() {
                    @Override
                    public void onResponse(Call<GetTransaksi> call, Response<GetTransaksi> response) {

                        Log.d("Transaksi", response.body().getStatus());
                        Log.d("Transaksi", response.body().getMessage());
                        Toast.makeText(mContext, "Berhasil beli", Toast.LENGTH_SHORT).show();
                        Intent mIntent = new Intent(getApplicationContext(), ActivityBarang.class);
                        startActivity(mIntent);

                    }

                    @Override
                    public void onFailure(Call<GetTransaksi> call, Throwable t) {
                        Log.d("Transaksi", t.getMessage());
                        Toast.makeText(mContext, "Gagal beli", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}
