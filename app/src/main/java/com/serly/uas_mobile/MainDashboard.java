package com.serly.uas_mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainDashboard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        SharedPreferences panggilNama = this.getSharedPreferences("simpanPrefLogin",Context.MODE_PRIVATE);
        String Username = panggilNama.getString("username","");

        //TextView nama = findViewById(R.id.txv_greeting);

       // nama.setText("Hai " + Username);
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
}
