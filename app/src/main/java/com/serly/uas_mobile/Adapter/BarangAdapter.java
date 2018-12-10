package com.serly.uas_mobile.Adapter;

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
import com.serly.uas_mobile.LayarDetailBarang;
import com.serly.uas_mobile.LayarDetailPembeli;
import com.serly.uas_mobile.LayarEditBarang;
import com.serly.uas_mobile.LayarEditPembeli;
import com.serly.uas_mobile.Model.Barang;
import com.serly.uas_mobile.Model.GetBarang;
import com.serly.uas_mobile.Model.GetPembeli;
import com.serly.uas_mobile.R;
import com.serly.uas_mobile.Rest.ApiClient;
import com.serly.uas_mobile.Rest.ApiInterfaceBarang;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.MyViewHolder> {
    List<Barang> mBarangList;
    ApiInterfaceBarang mApiInterfaceBarang;


    public BarangAdapter(List<Barang> barangList){
        mBarangList = barangList;
    }

    @Override
    public BarangAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_barang,parent,false);
        BarangAdapter.MyViewHolder mViewHolder = new BarangAdapter.MyViewHolder(mView);
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
    public void onBindViewHolder(BarangAdapter.MyViewHolder holder, final int position) {
        holder.mTextViewNamaBarang.setText(mBarangList.get(position).getNamaBarang());
        holder.mTextViewKategoriBarang.setText("Kategori : "+ mBarangList.get(position).getKategoriBarang());
        holder.mTextViewStok.setText("Stok : " + mBarangList.get(position).getStok());
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

                final CharSequence[] dialogitem = {"Lihat Barang","Update Barang","Hapus barang"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0 :
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
                                break;
                            case 1 :
                                Intent mIntent1 = new Intent(view.getContext(), LayarEditBarang.class);
                                mIntent1.putExtra("id_barang",mBarangList.get(position).getIdBarang());
                                mIntent1.putExtra("nama_barang" , mBarangList.get(position).getNamaBarang());
                                mIntent1.putExtra("warna_barang" , mBarangList.get(position).getWarnaBarang());
                                mIntent1.putExtra("kategori_barang", mBarangList.get(position).getKategoriBarang());
                                mIntent1.putExtra("berat_barang" , String.valueOf(mBarangList.get(position).getBeratBarang()));
                                mIntent1.putExtra("deskripsi" , mBarangList.get(position).getDeskripsi());
                                mIntent1.putExtra("harga", String.valueOf(mBarangList.get(position).getHarga()));
                                mIntent1.putExtra("stok" , String.valueOf(mBarangList.get(position).getStok()));
                                mIntent1.putExtra("foto" , mBarangList.get(position).getFoto());
                                view.getContext().startActivity(mIntent1);
                                break;
                            case 2 :
                                mApiInterfaceBarang = ApiClient.getClient().create(ApiInterfaceBarang.class);

                                RequestBody reqIdBarang = MultipartBody.create(MediaType.parse("multipart/form-data"),
                                        mBarangList.get(position).getIdBarang());
                                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),"delete");
                                Call<GetBarang> callDelete = mApiInterfaceBarang.deleteBarang(reqIdBarang,reqAction);
                                callDelete.enqueue(new Callback<GetBarang>() {
                                    @Override
                                    public void onResponse(Call<GetBarang> call, Response<GetBarang> response) {
                                        Toast toast = Toast.makeText(view.getContext(),"Retrofit Delete = " + response.body().getStatus() + "\n", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }

                                    @Override
                                    public void onFailure(Call<GetBarang> call, Throwable t) {
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
        return mBarangList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewNamaBarang ,mTextViewKategoriBarang,
                mTextViewStok, mTextViewharga;
        ImageView mImageFoto;
        public MyViewHolder(View itemView){
            super(itemView);
            mTextViewNamaBarang = (TextView) itemView.findViewById(R.id.tvNamaBarang);
            mImageFoto = (ImageView) itemView.findViewById(R.id.imgBarang);
            mTextViewKategoriBarang = (TextView) itemView.findViewById(R.id.tvKategoriBarang);
            mTextViewStok = (TextView) itemView.findViewById(R.id.tvStok);
            mTextViewharga = (TextView) itemView.findViewById(R.id.tvHarga);
        }
    }
}

