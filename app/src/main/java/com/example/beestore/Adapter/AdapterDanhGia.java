package com.example.beestore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.beestore.Model.ObjectClass.DanhGia;
import com.example.beestore.R;

import java.util.List;


public class AdapterDanhGia  extends RecyclerView.Adapter<AdapterDanhGia.ViewHolderDanhGia> {
    Context context;
    List<DanhGia> danhGiaList;
    int limit;

    public AdapterDanhGia(Context context,List<DanhGia> danhGiaList, int limit) {
        this.context=context;
        this.danhGiaList = danhGiaList;
        this.limit = limit;
    }

    public class ViewHolderDanhGia extends RecyclerView.ViewHolder {

        TextView txtTieuDeDanhGia,txtNoiDungDanhGia,txtDuocDanhGiaBoi;
        RatingBar rbDanhGia;

        public ViewHolderDanhGia(@NonNull View itemView) {
            super(itemView);
            txtDuocDanhGiaBoi=itemView.findViewById(R.id.txtDuocDangBoi);
            txtNoiDungDanhGia=itemView.findViewById(R.id.txtNoiDungDanhGia);
            txtTieuDeDanhGia=itemView.findViewById(R.id.txtTieuDeDanhGia);
            rbDanhGia=itemView.findViewById(R.id.rbDanhGia);
        }
    }
    @NonNull
    @Override
    public ViewHolderDanhGia onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.custom_layout_recycler_danhgia_chitiet,viewGroup,false);
        return new ViewHolderDanhGia(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDanhGia viewHolderDanhGia, int i) {
          DanhGia danhGia=danhGiaList.get(i);
          viewHolderDanhGia.txtTieuDeDanhGia.setText(danhGia.getTIEUDE());
          viewHolderDanhGia.txtNoiDungDanhGia.setText(danhGia.getNOIDUNG());
          viewHolderDanhGia.txtDuocDanhGiaBoi.setText("Được đánh giá bởi"+danhGia.getTENTHIETBI()+danhGia.getNGAYDG());
          viewHolderDanhGia.rbDanhGia.setRating(danhGia.getSOSAO());
    }

    @Override
    public int getItemCount() {
        int dong = 0;
        if(danhGiaList.size() < limit){
            dong = danhGiaList.size();
        }else{
            if(limit !=0){
                dong = limit;
            }else{
                dong = danhGiaList.size();
            }
        }

        return dong;
    }




}
