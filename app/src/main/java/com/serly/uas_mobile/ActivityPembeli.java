package com.serly.uas_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.serly.uas_mobile.Adapter.PembeliAdapter;
import com.serly.uas_mobile.Model.GetPembeli;
import com.serly.uas_mobile.Model.Pembeli;
import com.serly.uas_mobile.Rest.ApiClient;
import com.serly.uas_mobile.Rest.ApiInterfacePembeli;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPembeli extends AppCompatActivity {

    Button btGet;
    ApiInterfacePembeli mApiInterfacePembeli;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembeli);

        TextView txtInfo = (TextView)findViewById(R.id.txtInfo);
        if(getIntent() != null)
        {
            String info = getIntent().getStringExtra("info");
            txtInfo.setText(info);
        }
        btGet = (Button) findViewById(R.id.btGet);
//        btUpdate = (Button) findViewById(R.id.btUpdate);
//        btInsert = (Button) findViewById(R.id.btInsert);
//        btDelete = (Button) findViewById(R.id.btDelete);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mApiInterfacePembeli = ApiClient.getClient().create(ApiInterfacePembeli.class);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit2.Call<GetPembeli> pembelianCall = mApiInterfacePembeli.getPembeli();
                pembelianCall.enqueue(new Callback<GetPembeli>(){
                    @Override
                    public void onResponse(retrofit2.Call<GetPembeli> call, Response<GetPembeli> response) {
                        List<Pembeli> pembelianList = response.body().getListDataPembeli();
                        Log.d("Retrofit Get","Jumlah data pembelian:" +
                                String.valueOf(pembelianList.size()));

                        mAdapter = new PembeliAdapter(pembelianList);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(retrofit2.Call<GetPembeli> call, Throwable t) {
                        Log.e("Retrofit Get",t.toString());
                    }
                });
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mIntent;
        switch (item.getItemId()){
            case R.id.menuUpdateDataPembeli:
                mIntent = new Intent(this,LayarEditPembeli.class);
                startActivity(mIntent);
                return true;
            case R.id.menuListDataPembeli:
                mIntent = new Intent(this,ActivityPembeli.class);
                startActivity(mIntent);
                return true;
            case R.id.menuInsertDataPembeli:
                Intent intent = new Intent(this,LayarInsertPembeli.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void buttonInsertPembeli_onClick(View view) {
        Intent i = new Intent(this.getApplicationContext(),LayarInsertPembeli.class);
        this.startActivity(i);
    }

}

