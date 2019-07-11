package com.example.beestore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.beestore.Model.ObjectClass.ThuongHieu;
import com.example.beestore.R;
import com.example.beestore.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;


public class AdapterThuongHieuLon extends RecyclerView.Adapter<AdapterThuongHieuLon.ViewHolderThuongHieu> {
        Context context;
        List<ThuongHieu> thuongHieu;
        boolean kiemtra;


     public AdapterThuongHieuLon(Context context, List<ThuongHieu> thuongHieu,boolean kiemtra)
     {
               this.context=context;
               this.thuongHieu=thuongHieu;
               this.kiemtra=kiemtra;
     }



    public class ViewHolderThuongHieu extends RecyclerView.ViewHolder {

        TextView txtTieuDeThuongHieu;
        ImageView imgHinhThuongHieu;
        ProgressBar progressBar;
        LinearLayout linearLayout;
        public ViewHolderThuongHieu(@NonNull View itemView) {
            super(itemView);
          txtTieuDeThuongHieu=itemView.findViewById(R.id.txtTieuDeThuongHieuLonDienTu);
          imgHinhThuongHieu=itemView.findViewById(R.id.imgHinhThuongHieuLonDienTu);
          progressBar=itemView.findViewById(R.id.progress_bar_download);
          linearLayout=itemView.findViewById(R.id.lnThuongHieuLon);
        }

    }
    @NonNull
    @Override
    public ViewHolderThuongHieu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View view= LayoutInflater.from(context).inflate(R.layout.custom_layout_recycler_thuonghieulon,viewGroup,false);
         return new ViewHolderThuongHieu(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderThuongHieu viewHolderThuongHieu, int i) {
          final   ThuongHieu thuongHieus=thuongHieu.get(i);

          viewHolderThuongHieu.txtTieuDeThuongHieu.setText(thuongHieus.getTENTHUONGHIEU());


          viewHolderThuongHieu.linearLayout.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
              Intent iHienThiSanPhamTheoDanhMuc = new Intent(context, HienThiSanPhamTheoDanhMucActivity.class);
                iHienThiSanPhamTheoDanhMuc.putExtra("MALOAI",thuongHieus.getMATHUONGHIEU());
                iHienThiSanPhamTheoDanhMuc.putExtra("TENLOAI",thuongHieus.getTENTHUONGHIEU());
                iHienThiSanPhamTheoDanhMuc.putExtra("KIEMTRA",kiemtra);
                context.startActivity(iHienThiSanPhamTheoDanhMuc);

              }
          });

          Picasso.with(context).load(thuongHieus.getHINHTHUONGHIEU()).resize(150,90).centerInside().into(viewHolderThuongHieu.imgHinhThuongHieu, new Callback() {
              @Override
              public void onSuccess() {
                  viewHolderThuongHieu.progressBar.setVisibility(View.GONE);

              }

              @Override
              public void onError() {

              }
          });

     }



    @Override
    public int getItemCount() {
        return thuongHieu.size();
    }


}
