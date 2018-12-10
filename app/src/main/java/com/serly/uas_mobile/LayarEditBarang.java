package com.serly.uas_mobile;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.serly.uas_mobile.Model.GetBarang;
import com.serly.uas_mobile.Model.GetPembeli;
import com.serly.uas_mobile.Rest.ApiClient;
import com.serly.uas_mobile.Rest.ApiInterfaceBarang;
import com.serly.uas_mobile.Rest.ApiInterfacePembeli;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarEditBarang extends AppCompatActivity {
    Context mContext;
    EditText edtIdBarang, edtNamaBarang,edtWarnaBarang, edtkategoriBarang,
            edtBeratBarang, edtDeskripsi, edtHarga, edtStok ;
    ImageView imgFoto;
    Button btInsert, btUpdate, btDelete, btBack, btFoto, btCamera;
    TextView tvMessage;
    ApiInterfaceBarang mApiInterfaceBarang;
    String imagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_edit_barang);

        mContext = getApplicationContext();
        edtIdBarang =(EditText) findViewById(R.id.edtIdBarang);
        edtNamaBarang =(EditText) findViewById(R.id.edtNamaBarang);
        edtWarnaBarang =(EditText) findViewById(R.id.edtWarnaBarang);
        edtkategoriBarang =(EditText) findViewById(R.id.edtKategoriBarang);
        edtBeratBarang =(EditText) findViewById(R.id.edtBeratBarang);
        edtDeskripsi =(EditText) findViewById(R.id.edtDeskripsi);
        edtHarga =(EditText) findViewById(R.id.edtHarga);
        edtStok =(EditText) findViewById(R.id.edtStok);
        imgFoto =(ImageView) findViewById(R.id.imgFoto);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);

        btCamera= (Button) findViewById(R.id.btCamera);
        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtIdBarang.setText(mIntent.getStringExtra("id_barang"));
        edtNamaBarang.setText(mIntent.getStringExtra("nama_barang"));
        edtWarnaBarang.setText(mIntent.getStringExtra("warna_barang"));
        edtkategoriBarang.setText(mIntent.getStringExtra("kategori_barang"));
        edtBeratBarang.setText(mIntent.getStringExtra("berat_barang"));
        edtDeskripsi.setText(mIntent.getStringExtra("deskripsi"));
        edtHarga.setText(mIntent.getStringExtra("harga"));
        edtStok.setText(mIntent.getStringExtra("stok"));
//        imgFoto.(mIntent.getStringExtra("password"));

        Glide.with(mContext).load(ApiClient.BASE_URL+"uploads/"+mIntent.getStringExtra("foto")).into(imgFoto);

        mApiInterfaceBarang = ApiClient.getClient().create(ApiInterfaceBarang.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MultipartBody.Part body = null;
                if (!imagePath.isEmpty()){
                    // Buat file dari image yang dipilih
                    File file = new File(imagePath);

                    // Buat RequestBody instance dari file
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);

                    // MultipartBody.Part digunakan untuk mendapatkan nama file
                    body = MultipartBody.Part.createFormData("foto", file.getName(),
                            requestFile);
                }
                RequestBody reqIdBarang = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtIdBarang.getText().toString().isEmpty())?"":edtIdBarang.getText().toString());
                RequestBody reqNamaBarang = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtNamaBarang.getText().toString().isEmpty())?"":edtNamaBarang.getText().toString());
                RequestBody reqWarnabarang = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtWarnaBarang.getText().toString().isEmpty())?"":edtWarnaBarang.getText().toString());
                RequestBody reqKategoriBarang = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtkategoriBarang.getText().toString().isEmpty())?"":edtkategoriBarang.getText().toString());
                RequestBody reqBeratBarang = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtBeratBarang.getText().toString().isEmpty())?"":edtBeratBarang.getText().toString());
                RequestBody reqDeskripsi = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtDeskripsi.getText().toString().isEmpty())?"":edtDeskripsi.getText().toString());
                RequestBody reqHarga = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtHarga.getText().toString().isEmpty())?"":edtHarga.getText().toString());
                RequestBody reqStok = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtStok.getText().toString().isEmpty())?"":edtStok.getText().toString());

                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),"update");

                Call<GetBarang> callUpdate = mApiInterfaceBarang.putBarang(body,reqIdBarang,
                        reqNamaBarang,reqWarnabarang,reqKategoriBarang,reqBeratBarang,
                        reqDeskripsi,reqHarga,reqStok,reqAction);

                callUpdate.enqueue(new Callback<GetBarang>() {
                    @Override
                    public void onResponse(Call<GetBarang> call, Response<GetBarang> response) {
                        if(response.body().getStatus().equals("failed")){
                            tvMessage.setText("Retrofit Update \n Status = " +
                                    response.body().getStatus() );
                        }
                        else {
                            String detail = "\n" + "Data isi dengan lengkap" + "\n" ;
                            tvMessage.setText("Retrofit Update = " + response.body().getStatus() + "\n" );
                        }
                    }

                    @Override
                    public void onFailure(Call<GetBarang> call, Throwable t) {
                        tvMessage.setText("Retrofit Update \n Status = " + t.getMessage());
                    }
                });
            }
        });

        btBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(),ActivityBarang.class);
                startActivity(mIntent);
            }
        });
        btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_PICK);
                Intent intentChoose = Intent.createChooser(
                        galleryIntent,
                        "Pilih foto untuk di-upload");
                startActivityForResult(intentChoose, 10);
            }
        });

        btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isDeviceSupportCamera()) {
                    Toast.makeText(getApplicationContext(),"Camera di device anda tidak tersedia",
                            Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    mintaPermissions();
                }


            }
        });
    }


    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // Ok, device punya camera
            return true;
        } else {
            // Device masih mendol
            return false;
        }
    }

    private void mintaPermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // Cek apakah semua permission yang diperlukan sudah diijinkan
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(),
                                    "Semua permissions diijinkan!", Toast.LENGTH_SHORT).show();
                            tampilkanFotoDialog();
                        }

                        // Cek apakah ada permission yang tidak diijinkan
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // Info user untuk mengubah setting permission
                            tampilkanSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(
                            List<PermissionRequest> permissions,
                            PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(),
                                "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Jika tidak ada image yang dipilih maka return
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        // Jika request berasal dari Gallery
        if (requestCode == 13) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    imagePath = simpanImage(bitmap);
                    Toast.makeText(mContext, "Foto berhasil di-load!", Toast.LENGTH_SHORT).show();

                    Glide.with(mContext).load(new File(imagePath)).into(imgFoto);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, "Foto gagal di-load!", Toast.LENGTH_SHORT).show();
                }
            }

            // Jika request dari Camera
        } else if (requestCode == 16) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imagePath = simpanImage(thumbnail);
            Toast.makeText(mContext, "Foto berhasil di-load dari Camera!", Toast.LENGTH_SHORT)
                    .show();

            Glide.with(mContext).load(new File(imagePath)).into(imgFoto);
        }
    }

    private void tampilkanFotoDialog(){
        AlertDialog.Builder fotoDialog = new AlertDialog.Builder(this);
        fotoDialog.setTitle("Select Action");

        // Isi opsi dialog
        String[] fotoDialogItems = {
                "Pilih foto dari gallery",
                "Ambil dari kamera" };

        fotoDialog.setItems(fotoDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int pilihan) {
                        switch (pilihan) {
                            case 0:
                                pilihDariGallery();
                                break;
                            case 1:
                                ambilDariCamera();
                                break;
                        }
                    }
                });
        fotoDialog.show();
    }

    public void pilihDariGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, 13);
    }

    private void ambilDariCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(cameraIntent, 16);
    }

    public String simpanImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        // Kualitas gambar yang disimpan
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        // Buat object direktori file
        File lokasiImage = new File(
                Environment.getExternalStorageDirectory() + "/praktikum");

        // Buat direktori untuk penyimpanan
        if (!lokasiImage.exists()) {
            lokasiImage.mkdirs();
        }

        try {
            // Untuk penamaan file
            File f = new File(lokasiImage, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();

            // Operasi file
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();

            Log.d("PRAKTIKUM", "File tersimpan di --->" + f.getAbsolutePath());

            // Return file
            return f.getAbsolutePath();

        } catch (IOException e1) {
            Log.d("PRAKTIKUM", "erroraaaaa");
            e1.printStackTrace();
        }
        return "";
    }

    // Memberi peringatan butuh permission
    private void tampilkanSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LayarEditBarang.this);
        builder.setTitle("Butuh Permission");
        builder.setMessage("Aplikasi ini membutuhkan permission khusus untuk menjalankan aplikasi.");
        builder.setPositiveButton("BUKA SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                bukaSettings();
            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    // Membuka layar Settings Android
    private void bukaSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }
}

