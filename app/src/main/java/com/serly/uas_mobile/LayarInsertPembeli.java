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

import com.serly.uas_mobile.Model.GetPembeli;
import com.serly.uas_mobile.Rest.ApiClient;
import com.serly.uas_mobile.Rest.ApiInterfacePembeli;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarInsertPembeli extends AppCompatActivity {

    Context mContext;
    Button btInsert, btUpdate, btDelete, btBack;
    EditText edtIdPembeli, edtNamaPembeli,edtAlamat, edtTelpn, edtEmail, edtPassword;
    TextView tvAddMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_insert_pembeli);

        mContext = getApplicationContext();

        edtIdPembeli =(EditText) findViewById(R.id.edtAddIdPembeli);
        edtNamaPembeli =(EditText) findViewById(R.id.edtAddNamaPembeli);
        edtAlamat =(EditText) findViewById(R.id.edtAddAlamatPembeli);
        edtTelpn =(EditText) findViewById(R.id.edtAddTelpnPembeli);
        edtEmail =(EditText) findViewById(R.id.edtAddEmail);
        edtPassword =(EditText) findViewById(R.id.edtAddPassword);
        tvAddMessage =(TextView) findViewById(R.id.tvAddMessage);

        btInsert = (Button) findViewById(R.id.btInsert2);
        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btDelete = (Button) findViewById(R.id.btDelete2);
        btBack = (Button) findViewById(R.id.btBack);

        final ApiInterfacePembeli mApiInterface = ApiClient.getClient().create(ApiInterfacePembeli.class);

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterfacePembeli mApiInterface = ApiClient.getClient().create(ApiInterfacePembeli.class);

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
                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),"insert");
                Call<GetPembeli> mPembeliCall = mApiInterface.postPembeli(reqIdPembeli,reqNama,reqAlamat,reqTelp,reqEmail,reqPassword,reqAction);

                mPembeliCall.enqueue(new Callback<GetPembeli>() {
                    @Override
                    public void onResponse(Call<GetPembeli> call, Response<GetPembeli> response) {
                        if(response.body().getStatus().equals("failed")){
                            tvAddMessage.setText("Retrofit Insert = "+ response.body().getStatus() + "\n" );
                        }
                        else{
                            String detail = "\n" + "id_pembeli = " + response.body().getListDataPembeli().get(0).getIdPembeli() + "\n" +
                                    "nama_pembeli = " + response.body().getListDataPembeli().get(0).getNamaPembeli() + "\n" +
                                    "alamat = " + response.body().getListDataPembeli().get(0).getAlamat() + "\n" +
                                    "telp = " + response.body().getListDataPembeli().get(0).getTelpn() + "\n" +
                                    "email = " + response.body().getListDataPembeli().get(0).getEmail() + "\n" +
                                    "password = " + response.body().getListDataPembeli().get(0).getPassword() + "\n" ;
                            tvAddMessage.setText("Retrofit Insert \n Status = " + response.body().getStatus() + "\n" +
                                    "Message = " + response.body().getMessage() + detail);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetPembeli> call, Throwable t) {
                        tvAddMessage.setText("Retrofit Insert Failure \n Status = " + t.getMessage());
                    }
                });
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,LayarListPembeli.class);
                startActivity(intent);
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
                        tvAddMessage.setText("Retrofit Delete \n Status = " + response.body().getStatus() + "\n" +
                                "Message = " + response.body().getMessage() + "\n");
                    }

                    @Override
                    public void onFailure(Call<GetPembeli> call, Throwable t) {
                        tvAddMessage.setText("Retrofit Delete \n Status = " + t.getMessage());
                    }
                });
            }
        });
    }
}
