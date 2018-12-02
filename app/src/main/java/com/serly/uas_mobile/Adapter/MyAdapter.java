//package com.serly.uas_mobile.Adapter;
//
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.content.Intent;
//
//import com.serly.uas_mobile.LayarDetail;
//import com.serly.uas_mobile.Model.Pembeli;
//import com.serly.uas_mobile.R;
//
//import java.util.List;
//
//public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
//
//    List<Pembeli> mPembeliList;
//
//    public MyAdapter(List<Pembeli> pembeliList){
//        mPembeliList = pembeliList;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
//        MyViewHolder mViewHolder = new MyViewHolder(mView);
//        return mViewHolder;
//    }
//
////    @Override
////    public void onBindViewHolder(MyViewHolder holder, int position) {
////        holder.mTextViewIdPembeli.setText("Id Pembeli : " + mPembeliList.get(position).getId_pembeli());
////        holder.mTextViewIdPembeli.setText("Id Pembeli : " + mPembeliList.get(position).getId_pembeli());
////        holder.mTextViewIdTiket.setText("Id Tiket : "+ mPembeliList.get(position).getId_tiket());
////        holder.mTextViewTanggal.setText("Tanggal Beli : " + mPembeliList.get(position).getTanggal_beli());
////        holder.mTextViewTotalHarga.setText("Total Harga : " + String.valueOf(mPembeliList.get(position).getTotal_harga()));
////    }
//
//    @Override
//    public void onBindViewHolder(MyViewHolder holder,final int position) {
//        holder.mTextViewIdPembeli.setText("Id Pembeli : " + mPembeliList.get(position).getId_pembeli());
//        holder.mTextViewIdPembeli.setText("Id Pembeli : " + mPembeliList.get(position).getId_pembeli());
//        holder.mTextViewIdTiket.setText("Id Tiket : "+ mPembeliList.get(position).getId_tiket());
//        holder.mTextViewTanggal.setText("Tanggal Beli : " + mPembeliList.get(position).getTanggal_beli());
//        holder.mTextViewTotalHarga.setText("Total Harga : " + String.valueOf(mPembeliList.get(position).getTotal_harga()));
//
//        holder.itemView.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                Intent mIntent = new Intent(view.getContext(), LayarDetail.class);
//                mIntent.putExtra("id_pembeli",mPembeliList.get(position).getId_pembeli());
//                mIntent.putExtra("id_pembeli" , mPembeliList.get(position).getId_pembeli());
//                mIntent.putExtra("tanggal_beli : " , mPembeliList.get(position).getTanggal_beli());
//                mIntent.putExtra("id_tiket : ", mPembeliList.get(position).getId_tiket());
//                mIntent.putExtra("total_harga : " , String.valueOf(mPembeliList.get(position).getTotal_harga()));
//                view.getContext().startActivity(mIntent);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return mPembeliList.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView mTextViewIdPembeli, mTextViewIdPembeli, mTextViewTanggal,
//                mTextViewIdTiket, mTextViewTotalHarga;
//        public MyViewHolder(View itemView){
//            super(itemView);
//            mTextViewIdPembeli = (TextView) itemView.findViewById(R.id.tvIdPembeli);
//            mTextViewIdPembeli = (TextView) itemView.findViewById(R.id.tvIdPembeli);
//            mTextViewTanggal = (TextView) itemView.findViewById(R.id.tvTanggalBeli);
//            mTextViewIdTiket = (TextView) itemView.findViewById(R.id.tvIdTiket);
//            mTextViewTotalHarga = (TextView) itemView.findViewById(R.id.tvTotalHarga);
//        }
//    }
//}
//
