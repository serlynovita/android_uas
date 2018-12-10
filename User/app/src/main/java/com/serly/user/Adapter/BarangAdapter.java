package com.serly.user.Adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.serly.user.LayarDetailBarang;
import com.serly.user.Model.Barang;
import com.serly.user.R;
import com.serly.user.Rest.ApiClient;
import com.serly.user.Rest.ApiInterfaceBarang;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.MyViewHolder> {
    List<Barang> mBarangList;
    ApiInterfaceBarang mApiInterfaceBarang;


    public BarangAdapter(List<Barang> barangList){
        mBarangList = barangList;
    }

    @Override
    public BarangAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_barang,parent,false);
        BarangAdapter.MyViewHolder mViewHolder = new BarangAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(BarangAdapter.MyViewHolder holder, final int position) {
        holder.mTextViewNamaBarang.setText(mBarangList.get(position).getNamaBarang());
        holder.mTextViewharga.setText("Harga : " + mBarangList.get(position).getHarga());
        final String foto_url = ApiClient.BASE_URL + "uploads/" + mBarangList.get(position).getFoto();
        if (mBarangList.get(position).getFoto() != null ){
            Glide.with(holder.itemView.getContext()).load(foto_url)
                    .into(holder.mImageFoto);
        } else {
            Glide.with(holder.itemView.getContext()).load(R.drawable.image).into(holder
                    .mImageFoto);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(final View view) {
                Intent mIntent0 = new Intent(view.getContext(), LayarDetailBarang.class);
                mIntent0.putExtra("id_barang",mBarangList.get(position).getIdBarang());
                mIntent0.putExtra("nama_barang" , mBarangList.get(position).getNamaBarang());
                mIntent0.putExtra("warna_barang" , mBarangList.get(position).getWarnaBarang());
                mIntent0.putExtra("kategori_barang",mBarangList.get(position).getKategoriBarang());
                mIntent0.putExtra("berat_barang" , String.valueOf(mBarangList.get(position).getBeratBarang()));
                mIntent0.putExtra("deskripsi" , String.valueOf(mBarangList.get(position).getDeskripsi()));
                mIntent0.putExtra("harga", mBarangList.get(position).getHarga());
                mIntent0.putExtra("stok" , String.valueOf(mBarangList.get(position).getStok()));
                mIntent0.putExtra("foto" , mBarangList.get(position).getFoto());
                view.getContext().startActivity(mIntent0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBarangList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewNamaBarang, mTextViewharga;
        ImageView mImageFoto;
        public MyViewHolder(View itemView){
            super(itemView);
            mTextViewNamaBarang = (TextView) itemView.findViewById(R.id.nama_barang);
            mImageFoto = (ImageView) itemView.findViewById(R.id.gambar_barang);
            mTextViewharga = (TextView) itemView.findViewById(R.id.harga_barang);
        }
    }
}

