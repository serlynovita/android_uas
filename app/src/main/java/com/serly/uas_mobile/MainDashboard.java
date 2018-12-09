package com.serly.uas_mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainDashboard extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        SharedPreferences panggilNama = this.getSharedPreferences("simpanPrefLogin",Context.MODE_PRIVATE);
        String Username = panggilNama.getString("username","");

        //TextView nama = findViewById(R.id.txv_greeting);

       // nama.setText("Hai " + Username);

//        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
//
//        //Set Event
//        setSingleEvent(mainGrid);
        //setToggleEvent(mainGrid);
    }

    public void buttonLogout_onClick(View view) {
        SharedPreferences handler = this.getSharedPreferences("simpanPrefLogin", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = handler.edit();
        editor.clear();
        editor.commit();
        openHome();
    }

    private void openHome(){
        Intent i = new Intent(this.getApplicationContext(),MainActivity.class);
        this.startActivity(i);
    }

    public void buttonPembeli_onClick(View view) {
        Intent i = new Intent(this.getApplicationContext(),ActivityPembeli.class);
        this.startActivity(i);
    }

    public void buttonBarang_onClick(View view) {
        Intent i = new Intent(this.getApplicationContext(),ActivityBarang.class);
        this.startActivity(i);
    }

    public void buttonOngkosKirim_onClick(View view) {
        Intent i = new Intent(this.getApplicationContext(),ActivityOngkosKirim.class);
        this.startActivity(i);
    }

    public void buttonTransaksi_onClick(View view) {
        Intent i = new Intent(this.getApplicationContext(),ActivityPembeli.class);
        this.startActivity(i);
    }

    public void buttonDetailTransaksi_onClick(View view) {
        Intent i = new Intent(this.getApplicationContext(),ActivityPembeli.class);
        this.startActivity(i);
    }

//    private void setSingleEvent(GridLayout mainGrid) {
//        //Loop all child item of Main Grid
//        for (int i = 0; i < mainGrid.getChildCount(); i++) {
//            //You can see , all child item is CardView , so we just cast object to CardView
//            CardView cardView = (CardView) mainGrid.getChildAt(i);
//            final int finalI = i;
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(finalI!=5){
//                        Intent intent = new Intent(MainDashboard.this,ActivityPembeli.class);
//                        intent.putExtra("info","This is activity from card item index  "+finalI);
//                        startActivity(intent);
//                    }
//                    else{
//                        Intent intent = new Intent(MainDashboard.this,MainActivity.class);
//                        startActivity(intent);
//                    }
//
//
//                }
//            });
//        }
//    }
}
