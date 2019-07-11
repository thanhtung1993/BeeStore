package com.example.beestore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beestore.Model.ObjectClass.DienTu;
import com.example.beestore.R;

import java.util.List;


public class AdapterDienTu extends RecyclerView.Adapter<AdapterDienTu.ViewHolderDienTu> {
  Context context;
  List<DienTu> dienTuList;

   public AdapterDienTu(Context context, List<DienTu> dienTuList)
   {
       this.context=context;
       this.dienTuList=dienTuList;
   }
    public class ViewHolderDienTu extends RecyclerView.ViewHolder {
        ImageView imgHinhKhuyenMaiDienTu;
        RecyclerView recyclerViewThuongHieuLon,recyclerViewTopSanPham;
        TextView txtTieuDeSanPhamNoiBat,txtTopSanPhamNoiBat;
        public ViewHolderDienTu(@NonNull View itemView) {
            super(itemView);

            recyclerViewThuongHieuLon=itemView.findViewById(R.id.recyclerThuongHieuLon);
            recyclerViewTopSanPham=itemView.findViewById(R.id.recyclerTopDienThoaiVaMayTinhBang);
            imgHinhKhuyenMaiDienTu=itemView.findViewById(R.id.imgKhuyenMaiDienTu);
            txtTopSanPhamNoiBat = (TextView) itemView.findViewById(R.id.txtTenSanPhamNoiBat);
            txtTieuDeSanPhamNoiBat = (TextView) itemView.findViewById(R.id.txtTenTopSanPhamNoiBat);


        }
    }



    @NonNull
    @Override
    public ViewHolderDienTu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

         LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View view=inflater.inflate(R.layout.custom_layout_recyclerview_dientu,viewGroup,false);

         ViewHolderDienTu viewHolderDienTu=new ViewHolderDienTu(view);
         return viewHolderDienTu;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDienTu viewHolderDienTu, int i) {
         DienTu dienTu=dienTuList.get(i);

        viewHolderDienTu.txtTieuDeSanPhamNoiBat.setText(dienTu.getTennoibat().toString());
        viewHolderDienTu.txtTopSanPhamNoiBat.setText(dienTu.getTentopnoibat().toString());




        //xử lý adapter hiển thị danh sách thương hiệu lớn recycleview thuong hieu lon

          AdapterThuongHieuLon adapterThuongHieuLon=new AdapterThuongHieuLon(context,dienTu.getThuongHieus(),dienTu.isThuonghieu()) ;
          RecyclerView.LayoutManager layoutManager=new GridLayoutManager(context,3,GridLayoutManager.HORIZONTAL,false);
          viewHolderDienTu.recyclerViewThuongHieuLon.setLayoutManager(layoutManager);
          viewHolderDienTu.recyclerViewThuongHieuLon.setAdapter(adapterThuongHieuLon);
          adapterThuongHieuLon.notifyDataSetChanged();


        //xử lý adapter hiển thị danh sách thương hiệu lớn recycleview topsanpham
         AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(context,R.layout.custom_layout_topdienthoaivamaytinhbang,dienTu.getSanPhams());
         RecyclerView.LayoutManager layoutManager1=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
         viewHolderDienTu.recyclerViewTopSanPham.setLayoutManager(layoutManager1);
         viewHolderDienTu.recyclerViewTopSanPham.setAdapter(adapterTopDienThoaiDienTu);



    }

    @Override
    public int getItemCount() {
        return dienTuList.size();
    }


}
