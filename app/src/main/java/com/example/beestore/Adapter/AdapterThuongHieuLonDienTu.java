package com.example.beestore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.beestore.Model.ObjectClass.ThuongHieu;
import com.example.beestore.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterThuongHieuLonDienTu extends RecyclerView.Adapter<AdapterThuongHieuLonDienTu.ViewHolderThuongHieuLon> {
    Context context;
    List<ThuongHieu> thuongHieus;

    public AdapterThuongHieuLonDienTu(Context context, List<ThuongHieu> thuongHieus) {
        this.context = context;
        this.thuongHieus = thuongHieus;
    }

    public class ViewHolderThuongHieuLon extends RecyclerView.ViewHolder {
        ImageView imgLogoThuongHieuLon;
        public ViewHolderThuongHieuLon(@NonNull View itemView) {
            super(itemView);
            imgLogoThuongHieuLon=itemView.findViewById(R.id.imgLogoTopThuongHieuLon);
        }
    }
    @NonNull
    @Override
    public ViewHolderThuongHieuLon onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.custom_layout_topthuonghieulon_dientu,viewGroup,false);
        return new ViewHolderThuongHieuLon(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderThuongHieuLon viewHolderThuongHieuLon, int i) {
         ThuongHieu thuongHieu=thuongHieus.get(i);
        Picasso.with(context).load(thuongHieu.getHINHTHUONGHIEU()).resize(180,90).centerInside().into(viewHolderThuongHieuLon.imgLogoThuongHieuLon);
    }

    @Override
    public int getItemCount() {
        return thuongHieus.size();
    }

}
