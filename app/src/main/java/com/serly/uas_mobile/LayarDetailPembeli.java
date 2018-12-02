package com.serly.uas_mobile;

import retrofit2.Retrofit;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        btInsert = (Button) findViewById(R.id.btInsert2);
        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btDelete = (Button) findViewById(R.id.btDelete2);
        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtIdPembeli.setText(mIntent.getStringExtra("id_pembeli"));
        edtNamaPembeli.setText(mIntent.getStringExtra("nama_pembeli"));
        edtAlamat.setText(mIntent.getStringExtra("alamat"));
        edtTelpn.setText(mIntent.getStringExtra("telpn"));
        edtEmail.setText(mIntent.getStringExtra("email"));
        edtPassword.setText(mIntent.getStringExtra("password"));

        mApiInterfacePembeli = ApiClient.getClient().create(ApiInterfacePembeli.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDelPembeli> updatePembeliCall = mApiInterfacePembeli.putPembeli(
                        edtIdPembeli.getText().toString(),
                        edtNamaPembeli.getText().toString(),
                        edtAlamat.getText().toString(),
                        edtTelpn.getText().toString(),
                        edtEmail.getText().toString(),
                        edtPassword.getText().toString()
                );

                updatePembeliCall.enqueue(new Callback<PostPutDelPembeli>() {
                    @Override
                    public void onResponse(Call<PostPutDelPembeli> call, Response<PostPutDelPembeli> response) {
                        tvMessage.setText(" Retrofit Update : " + "\n" + " Status Update : "+
                                response.body().getStatus() + "\n" + " Message Update : " + response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<PostPutDelPembeli> call, Throwable t) {
                        tvMessage.setText("Retrofit Update : \n Status Update : " + t.getMessage());
                    }
                });
            }
        });

        btInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Call<PostPutDelPembeli> postPembelianCall = mApiInterfacePembeli.postPembeli(
                        edtIdPembeli.getText().toString(),
                        edtNamaPembeli.getText().toString(),
                        edtAlamat.getText().toString(),
                        edtTelpn.getText().toString(),
                        edtEmail.getText().toString(),
                        edtPassword.getText().toString()
                );
                postPembelianCall.enqueue(new Callback<PostPutDelPembeli>() {
                    @Override
                    public void onResponse(Call<PostPutDelPembeli> call, Response<PostPutDelPembeli> response) {
                        tvMessage.setText(" Retrofit Insert : " +
                                "\n" + " Status Insert : " + response.body().getStatus() + "\n" +
                                "Message Insert : " + response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<PostPutDelPembeli> call, Throwable t) {
                        tvMessage.setText("Retrofit Insert : \n Status Insert : " + t.getMessage());
                    }
                });
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!edtIdPembeli.getText().toString().trim().isEmpty()){
                    Call<PostPutDelPembeli> deletePembelian = mApiInterfacePembeli.deletePembeli(edtIdPembeli.getText().toString());
                    deletePembelian.enqueue(new Callback<PostPutDelPembeli>() {
                        @Override
                        public void onResponse(Call<PostPutDelPembeli> call, Response<PostPutDelPembeli> response) {
                            tvMessage.setText(" Retrofit Delte : " + "\n" +
                                    " Status Delete : " + response.body().getStatus() + "\n" +
                                    " Message Delete : " + response.body().getMessage());
                        }

                        @Override
                        public void onFailure(Call<PostPutDelPembeli> call, Throwable t) {
                            tvMessage.setText("Retrofit Delete : \n Status delete : " + t.getMessage());
                        }
                    });
                }
                else{
                    tvMessage.setText("id_pembeli harus diisi");
                }
            }
        });

        btBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(),ActivityPembeli.class);
                startActivity(mIntent);
            }
        });
    }
}

