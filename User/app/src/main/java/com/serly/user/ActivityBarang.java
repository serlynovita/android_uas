package com.serly.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.serly.user.Adapter.BarangAdapter;
import com.serly.user.Model.Barang;
import com.serly.user.Model.GetBarang;
import com.serly.user.Rest.ApiClient;
import com.serly.user.Rest.ApiInterfaceBarang;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;


public class ActivityBarang extends AppCompatActivity {

    Button btGet;
    ApiInterfaceBarang mApiInterfaceBarang;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);

        TextView txtInfo = (TextView)findViewById(R.id.txtInfo);
        if(getIntent() != null)
        {
            String info = getIntent().getStringExtra("info");
            txtInfo.setText(info);
        }
//        btUpdate = (Button) findViewById(R.id.btUpdate);
//        btInsert = (Button) findViewById(R.id.btInsert);
//        btDelete = (Button) findViewById(R.id.btDelete);


        mLayoutManager = new LinearLayoutManager(this);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));

        mApiInterfaceBarang = ApiClient.getClient().create(ApiInterfaceBarang.class);

        tampil();

//
//        btUpdate.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                retrofit2.Call<PostPutDelPembelian> updatePembelianCall =
//                        mApiInterface.putPembelian("16","11","2018-10-23","500000","11");
//                updatePembelianCall.enqueue(new Callback<PostPutDelPembelian>() {
//                    @Override
//                    public void onResponse(retrofit2.Call<PostPutDelPembelian> call, Response<PostPutDelPembelian> response) {
//                        Log.d("Retrofit Update","Status Update: " +
//                        String.valueOf(response.body().getStatus()));
//                    }
//
//                    @Override
//                    public void onFailure(retrofit2.Call<PostPutDelPembelian> call, Throwable t) {
//                        Log.d("Retrofit Update",t.getMessage());
//                    }
//                });
//            }
//        });
//
//        btInsert.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                retrofit2.Call<PostPutDelPembelian> postPembelianCall =
//                        mApiInterface.postPembelian("16","11","2018-10-23","500000","11");
//                postPembelianCall.enqueue(new Callback<PostPutDelPembelian>() {
//                    @Override
//                    public void onResponse(retrofit2.Call<PostPutDelPembelian> call, Response<PostPutDelPembelian> response) {
//                        Log.d("Retrofit Insert","Status Insert: "+
//                        String.valueOf(response.body().getStatus()));
//                    }
//
//                    @Override
//                    public void onFailure(retrofit2.Call<PostPutDelPembelian> call, Throwable t) {
//                        Log.d("Retrofit Insert",t.getMessage());
//                    }
//                });
//            }
//        });
//
//        btDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                retrofit2.Call<PostPutDelPembelian> deletePembelian =
//                        mApiInterface.deletePembelian("16");
//                deletePembelian.enqueue(new Callback<PostPutDelPembelian>() {
//                    @Override
//                    public void onResponse(retrofit2.Call<PostPutDelPembelian> call, Response<PostPutDelPembelian> response) {
//                        Log.i("Retrofit Delete","Status delete: "+
//                        String.valueOf(response.body().getStatus()));
//                    }
//
//                    @Override
//                    public void onFailure(retrofit2.Call<PostPutDelPembelian> call, Throwable t) {
//                        Log.i("Retrofit Delete",t.getMessage());
//                    }
//                });
//            }
//        });
    }

    public void tampil(){
        retrofit2.Call<GetBarang> barangCall = mApiInterfaceBarang.getBarang();
        barangCall.enqueue(new Callback<GetBarang>(){
            @Override
            public void onResponse(retrofit2.Call<GetBarang> call, Response<GetBarang> response) {
                List<Barang> barangList = response.body().getResult();
                Log.d("Retrofit Get","Jumlah data barang:" + String.valueOf(barangList.size()));
                mAdapter = new BarangAdapter(barangList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(retrofit2.Call<GetBarang> call, Throwable t) {
                Log.e("Retrofit Get",t.toString());
            }
        });
    }

    public void buttonBackBarang_onClick(View view) {
        Intent i = new Intent(this.getApplicationContext(),MainActivity.class);
        this.startActivity(i);
    }
}

