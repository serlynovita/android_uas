package com.serly.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btPilihBarang,btLihatTransaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btPilihBarang = (Button) findViewById(R.id.btnPilihBarang);
        btLihatTransaksi = (Button) findViewById(R.id.btnLihatTransaksi);

        btPilihBarang.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntent0 = new Intent(getApplicationContext(), ActivityBarang.class);
                startActivity(mIntent0);
            }
        });

        btLihatTransaksi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(), ActivityLihatTransaksi.class);
                startActivity(mIntent);
            }
        });
    }
}
