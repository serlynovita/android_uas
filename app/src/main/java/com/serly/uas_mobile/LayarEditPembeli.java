package com.serly.uas_mobile;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.serly.uas_mobile.Model.GetPembeli;
import com.serly.uas_mobile.Rest.ApiClient;
import com.serly.uas_mobile.Rest.ApiInterfacePembeli;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarEditPembeli extends AppCompatActivity {

    EditText edtIdPembeli, edtNamaPembeli,edtAlamat, edtTelpn, edtEmail, edtPassword;
    TextView tvMessage;
    Context mContext;
    Button btInsert, btUpdate, btDelete, btBack;
    TextView tvAddMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_edit_pembeli);
        mContext = getApplicationContext();

        edtIdPembeli =(EditText) findViewById(R.id.edtAddIdPembeli);
        edtNamaPembeli =(EditText) findViewById(R.id.edtAddNamaPembeli);
        edtAlamat =(EditText) findViewById(R.id.edtAddAlamatPembeli);
        edtTelpn =(EditText) findViewById(R.id.edtAddTelpnPembeli);
        edtEmail =(EditText) findViewById(R.id.edtAddEmail);
        edtPassword =(EditText) findViewById(R.id.edtAddPassword);
        tvMessage =(TextView) findViewById(R.id.tvAddMessage);

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

        setListener();
    }

    private void setListener(){
        final ApiInterfacePembeli mApiInterface = ApiClient.getClient().create(ApiInterfacePembeli.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestBody reqIdPembeli = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtIdPembeli.getText().toString().isEmpty())?"":edtIdPembeli.getText().toString());
                RequestBody reqNama = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtNamaPembeli.getText().toString().isEmpty())?"":edtNamaPembeli.getText().toString());
                RequestBody reqAlamat = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAlamat.getText().toString().isEmpty())?"":edtAlamat.getText().toString());
                RequestBody reqTelp = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtTelpn.getText().toString().isEmpty())?"":edtTelpn.getText().toString());
                RequestBody reqEmail = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtEmail.getText().toString().isEmpty())?"":edtEmail.getText().toString());
                RequestBody reqPassword = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtPassword.getText().toString().isEmpty())?"":edtPassword.getText().toString());
                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),"update");

                Call<GetPembeli> callUpdate = mApiInterface.putPembeli(reqIdPembeli,reqNama,reqAlamat,reqTelp,reqEmail,reqPassword,reqAction);

                callUpdate.enqueue(new Callback<GetPembeli>() {
                    @Override
                    public void onResponse(Call<GetPembeli> call, Response<GetPembeli> response) {
                        if(response.body().getStatus().equals("failed")){
                            tvMessage.setText("Retrofit Update \n Status = " +
                                    response.body().getStatus() + "\n" + "Message = " +
                                    response.body().getMessage() + "\n");
                        }
                        else {
                            String detail = "\n" + "id_pembeli = " + response.body().getListDataPembeli().get(0).getIdPembeli() + "\n" +
                                    "nama_pembeli = " + response.body().getListDataPembeli().get(0).getNamaPembeli() + "\n" +
                                    "alamat = " + response.body().getListDataPembeli().get(0).getAlamat() + "\n" +
                                    "telp = " + response.body().getListDataPembeli().get(0).getTelpn() + "\n" +
                                    "email = " + response.body().getListDataPembeli().get(0).getEmail() + "\n" +
                                    "password = " + response.body().getListDataPembeli().get(0).getPassword() + "\n" ;
                            tvMessage.setText("Retrofit Update \n Status = " + response.body().getStatus() + "\n" +
                                    "Message = " + response.body().getMessage() + detail);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetPembeli> call, Throwable t) {
                        tvMessage.setText("Retrofit Update \n Status = " + t.getMessage());
                    }
                });
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestBody reqIdPembeli = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtIdPembeli.getText().toString().isEmpty())? "" : edtIdPembeli.getText().toString());
                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),"delete");
                Call<GetPembeli> callDelete = mApiInterface.deletePembeli(reqIdPembeli,reqAction);
                callDelete.enqueue(new Callback<GetPembeli>() {
                    @Override
                    public void onResponse(Call<GetPembeli> call, Response<GetPembeli> response) {
                        tvMessage.setText("Retrofit Delete \n Status = " + response.body().getStatus() + "\n" +
                                "Message = " + response.body().getMessage() + "\n");
                    }

                    @Override
                    public void onFailure(Call<GetPembeli> call, Throwable t) {
                        tvMessage.setText("Retrofit Delete \n Status = " + t.getMessage());
                    }
                });
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tempIntent = new Intent(mContext,LayarListPembeli.class);
                startActivity(tempIntent);
            }
        });
    }
}