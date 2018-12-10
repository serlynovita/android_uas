package com.serly.user.Adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.serly.user.Model.Transaksi;
import com.serly.user.R;
import com.serly.user.Rest.ApiInterfaceTransaksi;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.MyViewHolder> {
        List<Transaksi> mTransaksiList;
        ApiInterfaceTransaksi mApiInterfaceTransaksi;


public TransaksiAdapter(List<Transaksi> transaksiList){
        mTransaksiList = transaksiList;
        }

@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_transaksi,parent,false);
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
        holder.mTextViewIdPembeli.setText(mTransaksiList.get(position).getIdPembeli());
        holder.mTextViewTotalHarga.setText("Total Harga : "+ mTransaksiList.get(position).getTotalHarga());
        holder.mTextViewStatus.setText("Status : " + mTransaksiList.get(position).getStatus());
        holder.mTextViewTglBeli.setText("Tanggal Beli : " + mTransaksiList.get(position).getTglBeli());
        }

@Override
public int getItemCount() {
        return mTransaksiList.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView mTextViewIdPembeli ,mTextViewTotalHarga,
            mTextViewStatus, mTextViewTglBeli;
    public MyViewHolder(View itemView){
        super(itemView);
        mTextViewIdPembeli = (TextView) itemView.findViewById(R.id.tvIdPembeli);
        mTextViewTotalHarga = (TextView) itemView.findViewById(R.id.tvTotalHarga);
        mTextViewStatus = (TextView) itemView.findViewById(R.id.tvStatus);
        mTextViewTglBeli = (TextView) itemView.findViewById(R.id.tvTglBeli);
    }
}
}

