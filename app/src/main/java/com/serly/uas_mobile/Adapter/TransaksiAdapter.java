package com.serly.uas_mobile.Adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.serly.uas_mobile.LayarDetailTransaksi;
import com.serly.uas_mobile.LayarEditTransaksi;
import com.serly.uas_mobile.Model.GetTransaksi;
import com.serly.uas_mobile.Model.Transaksi;
import com.serly.uas_mobile.R;
import com.serly.uas_mobile.Rest.ApiClient;
import com.serly.uas_mobile.Rest.ApiInterfaceTransaksi;

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

        holder.itemView.setOnClickListener(new View.OnClickListener(){

@Override
public void onClick(final View view) {

final CharSequence[] dialogitem = {"Lihat Transaksi","Update Transaksi","Hapus Transaksi"};
final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        builder.setTitle("Pilihan");
        builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialog, int item) {
        switch (item){
        case 0 :
        Intent mIntent0 = new Intent(view.getContext(), LayarDetailTransaksi.class);
        mIntent0.putExtra("id_transaksi",mTransaksiList.get(position).getIdTransaksi());
        mIntent0.putExtra("id_pembeli" , mTransaksiList.get(position).getIdPembeli());
        mIntent0.putExtra("id_ongkir" , mTransaksiList.get(position).getIdOngkir());
        mIntent0.putExtra("total_harga", mTransaksiList.get(position).getTotalHarga());
        mIntent0.putExtra("tgl_beli" , String.valueOf(mTransaksiList.get(position).getTglBeli()));
        mIntent0.putExtra("status" , String.valueOf(mTransaksiList.get(position).getStatus()));
        view.getContext().startActivity(mIntent0);
        break;
        case 1 :
        Intent mIntent1 = new Intent(view.getContext(), LayarEditTransaksi.class);
                mIntent1.putExtra("id_transaksi",mTransaksiList.get(position).getIdTransaksi());
                mIntent1.putExtra("id_pembeli" , mTransaksiList.get(position).getIdPembeli());
                mIntent1.putExtra("id_ongkir" , mTransaksiList.get(position).getIdOngkir());
                mIntent1.putExtra("total_harga", mTransaksiList.get(position).getTotalHarga());
                mIntent1.putExtra("tgl_beli" , String.valueOf(mTransaksiList.get(position).getTglBeli()));
                mIntent1.putExtra("status" , String.valueOf(mTransaksiList.get(position).getStatus()));
        view.getContext().startActivity(mIntent1);
        break;
        case 2 :
        mApiInterfaceTransaksi = ApiClient.getClient().create(ApiInterfaceTransaksi.class);

        RequestBody reqIdTransaksi = MultipartBody.create(MediaType.parse("multipart/form-data"),
                mTransaksiList.get(position).getIdTransaksi());
        RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),"delete");
        Call<GetTransaksi> callDelete = mApiInterfaceTransaksi.deleteTransaksi(reqIdTransaksi,reqAction);
        callDelete.enqueue(new Callback<GetTransaksi>() {
        @Override
        public void onResponse(Call<GetTransaksi> call, Response<GetTransaksi> response) {
                Toast toast = Toast.makeText(view.getContext(),"Retrofit Delete = " + response.body().getStatus() + "\n", Toast.LENGTH_SHORT);
                toast.show();
                }

        @Override
        public void onFailure(Call<GetTransaksi> call, Throwable t) {
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

