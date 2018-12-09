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

import com.serly.uas_mobile.Adapter.OngkosKirimAdapter;
import com.serly.uas_mobile.Adapter.PembeliAdapter;
import com.serly.uas_mobile.Model.GetOngkosKirim;
import com.serly.uas_mobile.Model.GetPembeli;
import com.serly.uas_mobile.Model.OngkosKirim;
import com.serly.uas_mobile.Model.Pembeli;
import com.serly.uas_mobile.Rest.ApiClient;
import com.serly.uas_mobile.Rest.ApiInterfaceOngkosKirim;
import com.serly.uas_mobile.Rest.ApiInterfacePembeli;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class ActivityOngkosKirim extends AppCompatActivity {

    Button btGet;
    ApiInterfaceOngkosKirim mApiInterfaceOngkosKirim;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongkos_kirim);

        TextView txtInfo = (TextView)findViewById(R.id.txtInfo);
        if(getIntent() != null)
        {
            String info = getIntent().getStringExtra("info");
            txtInfo.setText(info);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mApiInterfaceOngkosKirim = ApiClient.getClient().create(ApiInterfaceOngkosKirim.class);

        tampil();
    }



    public void tampil(){
        retrofit2.Call<GetOngkosKirim> ongkosKirimCall = mApiInterfaceOngkosKirim.getOngkosKirim();
        ongkosKirimCall.enqueue(new Callback<GetOngkosKirim>(){
            @Override
            public void onResponse(retrofit2.Call<GetOngkosKirim> call, Response<GetOngkosKirim> response) {
                List<OngkosKirim> ongkosKirimList = response.body().getListDataOngkosKirim();
                Log.d("Retrofit Get","Jumlah data pembelian:" + String.valueOf(ongkosKirimList.size()));
                mAdapter = new OngkosKirimAdapter(ongkosKirimList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(retrofit2.Call<GetOngkosKirim> call, Throwable t) {
                Log.e("Retrofit Get",t.toString());
            }
        });
    }

    public void buttonInsertOngkosKirim_onClick(View view) {
        Intent i = new Intent(this.getApplicationContext(),LayarInsertOngkosKirim.class);
        this.startActivity(i);
    }

    public void buttonBackOngkosKirim_onClick(View view) {
        Intent i = new Intent(this.getApplicationContext(),MainDashboard.class);
        this.startActivity(i);
    }

    public void buttonRefreshOngkosKirim_onClick(View view) {
        this.startActivity(getIntent());
        finish();
    }
}

