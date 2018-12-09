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

import com.serly.uas_mobile.LayarDetailOngkosKirim;
import com.serly.uas_mobile.LayarDetailPembeli;
import com.serly.uas_mobile.LayarEditOngkosKirim;
import com.serly.uas_mobile.LayarEditPembeli;
import com.serly.uas_mobile.LayarInsertOngkosKirim;
import com.serly.uas_mobile.Model.GetOngkosKirim;
import com.serly.uas_mobile.Model.GetPembeli;
import com.serly.uas_mobile.Model.OngkosKirim;
import com.serly.uas_mobile.R;
import com.serly.uas_mobile.Rest.ApiClient;
import com.serly.uas_mobile.Rest.ApiInterfaceOngkosKirim;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OngkosKirimAdapter extends RecyclerView.Adapter<OngkosKirimAdapter.MyViewHolder> {
    List<OngkosKirim> mOngkosKirimList;
    ApiInterfaceOngkosKirim mApiInterfaceOngkosKirim;


    public OngkosKirimAdapter(List<OngkosKirim> ongkosKirimList){
        mOngkosKirimList = ongkosKirimList;
    }

    @Override
    public OngkosKirimAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ongkoskirim,parent,false);
        OngkosKirimAdapter.MyViewHolder mViewHolder = new OngkosKirimAdapter.MyViewHolder(mView);
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
    public void onBindViewHolder(OngkosKirimAdapter.MyViewHolder holder, final int position) {
        holder.mTextViewKota.setText(mOngkosKirimList.get(position).getKota());
        holder.mTextViewHarga.setText("Harga : "+ mOngkosKirimList.get(position).getHarga());

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(final View view) {

                final CharSequence[] dialogitem = {"Lihat Ongkos Kirim","Update Ongkos Kirim","Hapus Ongkos Kirim"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0 :
                                Intent mIntent0 = new Intent(view.getContext(), LayarDetailOngkosKirim.class);
                                mIntent0.putExtra("id_ongkir",mOngkosKirimList.get(position).getIdOngkir());
                                mIntent0.putExtra("kota" , mOngkosKirimList.get(position).getKota());
                                mIntent0.putExtra("harga" , mOngkosKirimList.get(position).getHarga());
                                view.getContext().startActivity(mIntent0);
                                break;
                            case 1 :
                                Intent mIntent1 = new Intent(view.getContext(), LayarEditOngkosKirim.class);
                                mIntent1.putExtra("id_ongkir", mOngkosKirimList.get(position).getIdOngkir());
                                mIntent1.putExtra("kota" , mOngkosKirimList.get(position).getKota());
                                mIntent1.putExtra("harga" , mOngkosKirimList.get(position).getHarga());
                                view.getContext().startActivity(mIntent1);
                                break;
                            case 2 :
                                mApiInterfaceOngkosKirim = ApiClient.getClient().create(ApiInterfaceOngkosKirim.class);

                                RequestBody reqIdOnkosKirim = MultipartBody.create(MediaType.parse("multipart/form-data"),
                                        mOngkosKirimList.get(position).getIdOngkir());
                                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),"delete");
                                Call<GetOngkosKirim> callDelete = mApiInterfaceOngkosKirim.deleteOngkosKirim(reqIdOnkosKirim,reqAction);
                                callDelete.enqueue(new Callback<GetOngkosKirim>() {
                                    @Override
                                    public void onResponse(Call<GetOngkosKirim> call, Response<GetOngkosKirim> response) {
                                        Toast toast = Toast.makeText(view.getContext(),"Retrofit Delete = " + response.body().getStatus() + "\n", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }

                                    @Override
                                    public void onFailure(Call<GetOngkosKirim> call, Throwable t) {
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
        return mOngkosKirimList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewIdOngkir ,mTextViewKota,
                mTextViewHarga;
        public MyViewHolder(View itemView){
            super(itemView);
            mTextViewKota = (TextView) itemView.findViewById(R.id.tvKota);
            mTextViewHarga = (TextView) itemView.findViewById(R.id.tvHarga);
        }
    }
}

