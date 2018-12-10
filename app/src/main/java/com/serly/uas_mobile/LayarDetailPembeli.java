package com.serly.uas_mobile;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.serly.uas_mobile.Model.GetPembeli;
import com.serly.uas_mobile.Model.PostPutDelPembeli;
import com.serly.uas_mobile.Rest.ApiClient;
import com.serly.uas_mobile.Rest.ApiInterfacePembeli;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarDetailPembeli extends AppCompatActivity {

    EditText edtIdPembeli, edtNamaPembeli,edtAlamat, edtTelpn, edtEmail, edtPassword;
    Button btInsert, btUpdate, btDelete, btBack;
    TextView tvMessage;
    ApiInterfacePembeli mApiInterfacePembeli;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_detail_pembeli);

        edtIdPembeli =(EditText) findViewById(R.id.edtIdPembeli);
        edtNamaPembeli =(EditText) findViewById(R.id.edtNamaPembeli);
        edtAlamat =(EditText) findViewById(R.id.edtAlamat);
        edtTelpn =(EditText) findViewById(R.id.edtTelpn);
        edtEmail =(EditText) findViewById(R.id.edtEmail);
        edtPassword =(EditText) findViewById(R.id.edtPassword);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);

        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtIdPembeli.setText(mIntent.getStringExtra("id_pembeli"));
        edtNamaPembeli.setText(mIntent.getStringExtra("nama_pembeli"));
        edtAlamat.setText(mIntent.getStringExtra("alamat"));
        edtTelpn.setText(mIntent.getStringExtra("telpn"));
        edtEmail.setText(mIntent.getStringExtra("email"));
        edtPassword.setText(mIntent.getStringExtra("password"));

        mApiInterfacePembeli = ApiClient.getClient().create(ApiInterfacePembeli.class);

        btBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(),ActivityPembeli.class);
                startActivity(mIntent);
            }
        });
    }
}

