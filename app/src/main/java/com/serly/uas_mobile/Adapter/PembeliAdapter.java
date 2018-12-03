package com.serly.uas_mobile.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.serly.uas_mobile.LayarDetailPembeli;
import com.serly.uas_mobile.Model.Pembeli;
import com.serly.uas_mobile.R;

import org.w3c.dom.Text;

import java.util.List;

public class PembeliAdapter extends RecyclerView.Adapter<PembeliAdapter.MyViewHolder> {
    List<Pembeli> mPembeliList;

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
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), LayarDetailPembeli.class);
                mIntent.putExtra("id_pembeli",mPembeliList.get(position).getIdPembeli());
                mIntent.putExtra("nama_pembeli" , mPembeliList.get(position).getNamaPembeli());
                mIntent.putExtra("alamat" , mPembeliList.get(position).getAlamat());
                mIntent.putExtra("telpn", mPembeliList.get(position).getTelpn());
                mIntent.putExtra("email" , String.valueOf(mPembeliList.get(position).getEmail()));
                mIntent.putExtra("password" , String.valueOf(mPembeliList.get(position).getPassword()));
                view.getContext().startActivity(mIntent);
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

