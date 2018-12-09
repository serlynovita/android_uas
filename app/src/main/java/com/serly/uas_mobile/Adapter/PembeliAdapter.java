package com.serly.uas_mobile.Adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.serly.uas_mobile.ActivityPembeli;
import com.serly.uas_mobile.LayarDetailPembeli;
import com.serly.uas_mobile.LayarEditPembeli;
import com.serly.uas_mobile.MainActivity;
import com.serly.uas_mobile.Model.GetPembeli;
import com.serly.uas_mobile.Model.Pembeli;
import com.serly.uas_mobile.R;

import org.w3c.dom.Text;

import com.serly.uas_mobile.ActivityPembeli;
import com.serly.uas_mobile.Rest.ApiClient;
import com.serly.uas_mobile.Rest.ApiInterfacePembeli;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembeliAdapter extends RecyclerView.Adapter<PembeliAdapter.MyViewHolder> {
    List<Pembeli> mPembeliList;
    ApiInterfacePembeli mApiInterfacePembeli;


    public PembeliAdapter(List<Pembeli> pembeliList){
        mPembeliList = pembeliList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pembeli,parent,false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position) {
//        holder.mTextViewIdPembelian.setText("Id Pembelian : " + mPembelianList.get(position).getId_pembelian());
//        holder.mTextViewIdPembeli.setText("Id Pembeli : " + mPembelianList.get(position).getId_pembeli());
//        holder.mTextViewIdTiket.setText("Id Tiket : "+ mPembelianList.get(position).getId_tiket());
//        holder.mTextViewTanggal.setText("Tanggal Beli : " + mPembelianList.get(position).getTanggal_beli());
//        holder.mTextViewTotalHarga.setText("Total Harga : " + String.valueOf(mPembelianList.get(position).getTotal_harga()));
//    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        holder.mTextViewNama.setText(mPembeliList.get(position).getNamaPembeli());
        holder.mTextViewAlamat.setText("Alamat : "+ mPembeliList.get(position).getAlamat());
        holder.mTextViewTelepon.setText("Telepon : " + mPembeliList.get(position).getTelpn());

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(final View view) {

                final CharSequence[] dialogitem = {"Lihat Pembeli","Update Pembeli","Hapus Pembeli"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0 :
                                Intent mIntent0 = new Intent(view.getContext(), LayarDetailPembeli.class);
                                mIntent0.putExtra("id_pembeli",mPembeliList.get(position).getIdPembeli());
                                mIntent0.putExtra("nama_pembeli" , mPembeliList.get(position).getNamaPembeli());
                                mIntent0.putExtra("alamat" , mPembeliList.get(position).getAlamat());
                                mIntent0.putExtra("telpn", mPembeliList.get(position).getTelpn());
                                mIntent0.putExtra("email" , String.valueOf(mPembeliList.get(position).getEmail()));
                                mIntent0.putExtra("password" , String.valueOf(mPembeliList.get(position).getPassword()));
                                view.getContext().startActivity(mIntent0);
                                break;
                            case 1 :
                                Intent mIntent1 = new Intent(view.getContext(), LayarEditPembeli.class);
                                mIntent1.putExtra("id_pembeli",mPembeliList.get(position).getIdPembeli());
                                mIntent1.putExtra("nama_pembeli" , mPembeliList.get(position).getNamaPembeli());
                                mIntent1.putExtra("alamat" , mPembeliList.get(position).getAlamat());
                                mIntent1.putExtra("telpn", mPembeliList.get(position).getTelpn());
                                mIntent1.putExtra("email" , String.valueOf(mPembeliList.get(position).getEmail()));
                                mIntent1.putExtra("password" , String.valueOf(mPembeliList.get(position).getPassword()));
                                view.getContext().startActivity(mIntent1);
                                break;
                            case 2 :
                                mApiInterfacePembeli = ApiClient.getClient().create(ApiInterfacePembeli.class);

                                RequestBody reqIdPembeli = MultipartBody.create(MediaType.parse("multipart/form-data"),
                                        mPembeliList.get(position).getIdPembeli());
                                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),"delete");
                                Call<GetPembeli> callDelete = mApiInterfacePembeli.deletePembeli(reqIdPembeli,reqAction);
                                callDelete.enqueue(new Callback<GetPembeli>() {
                                    @Override
                                    public void onResponse(Call<GetPembeli> call, Response<GetPembeli> response) {
                                        Toast toast = Toast.makeText(view.getContext(),"Retrofit Delete = " + response.body().getStatus() + "\n", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }

                                    @Override
                                    public void onFailure(Call<GetPembeli> call, Throwable t) {
//                                        tvMessage.setText("Retrofit Delete \n Status = " + t.getMessage());
                                        Toast toast = Toast.makeText(view.getContext(),"Retrofit Delete \n Status = " + t.getMessage(), Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                });
                                break;
                        }
                    }
                });
                builder.create().show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return mPembeliList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewIdPembeli ,mTextViewNama,
                mTextViewAlamat, mTextViewTelepon,mTextViewEmail,mTextViewPassword;
        public MyViewHolder(View itemView){
            super(itemView);
            mTextViewNama = (TextView) itemView.findViewById(R.id.tvNama);
            mTextViewAlamat = (TextView) itemView.findViewById(R.id.tvAlamat);
            mTextViewTelepon = (TextView) itemView.findViewById(R.id.tvTelp);
        }
    }
}

