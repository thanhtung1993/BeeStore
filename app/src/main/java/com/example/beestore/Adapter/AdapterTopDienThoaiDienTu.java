package com.example.beestore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beestore.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.beestore.Model.ObjectClass.SanPham;
import com.example.beestore.R;
import com.example.beestore.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterTopDienThoaiDienTu extends RecyclerView.Adapter<AdapterTopDienThoaiDienTu.ViewHolderTopDienThoai> {
   Context context;
   List<SanPham> sanPhamList;
   //int layout;

    public AdapterTopDienThoaiDienTu(Context context,int layout, List<SanPham> sanPhamList) {
        this.context = context;
      //  this.layout=layout;
        this.sanPhamList = sanPhamList;
    }

    public class ViewHolderTopDienThoai extends RecyclerView.ViewHolder {
        ImageView imgHinhSanPham;
        TextView txtTenSP,txtGiaTien,txtGiamGia;
        CardView cardView;
        public ViewHolderTopDienThoai(@NonNull View itemView) {
            super(itemView);
            imgHinhSanPham=itemView.findViewById(R.id.imgTopDienThoaiDienTu);
            txtTenSP=itemView.findViewById(R.id.txtTieuDeTopDienThoaiDienTu);
            txtGiaTien=itemView.findViewById(R.id.txtGiaDienTu);
            txtGiamGia=itemView.findViewById(R.id.txtDienTuGiamGia);
            cardView=itemView.findViewById(R.id.cardview);

        }
    }




    @NonNull
    @Override
    public ViewHolderTopDienThoai onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_layout_topdienthoaivamaytinhbang,viewGroup,false);
        return new ViewHolderTopDienThoai(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTopDienThoai viewHolderTopDienThoai, int i) {
        SanPham sanPham=sanPhamList.get(i);

        Picasso.with(context).load(sanPham.getANHLON()).resize(140,140).centerInside().into(viewHolderTopDienThoai.imgHinhSanPham);
        viewHolderTopDienThoai.txtTenSP.setText(sanPham.getTENSP());
     // xử lý giảm giá

//--------------------------------------------------------------------------------
     ChiTietKhuyenMai chiTietKhuyenMai=sanPham.getChiTietKhuyenMai();
        int giatien=sanPham.getGIA();
        if (chiTietKhuyenMai !=null)
        {
            int phamtramkhuyenmai=chiTietKhuyenMai.getPHANTRAMKM();


            NumberFormat numberFormat=new DecimalFormat("###,###");
            String gia=numberFormat.format(giatien);

            viewHolderTopDienThoai.txtGiamGia.setVisibility(View.VISIBLE);
            viewHolderTopDienThoai.txtGiamGia.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolderTopDienThoai.txtGiamGia.setText(gia +"VND");
            giatien=giatien*phamtramkhuyenmai/100;
        }
//--------------------------------------------------------------------------------


        NumberFormat numberFormat=new DecimalFormat("###,###");
        String gia=numberFormat.format(giatien);
        viewHolderTopDienThoai.txtGiaTien.setText(String.valueOf(gia+"VND"));

        viewHolderTopDienThoai.cardView.setTag(sanPham.getMASP());

        viewHolderTopDienThoai.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ichitietsanpham=new Intent(context, ChiTietSanPhamActivity.class);
                ichitietsanpham.putExtra("masp", (int)v.getTag());

                context.startActivity(ichitietsanpham);
            }
        });


    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


 }
