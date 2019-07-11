package com.example.beestore.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beestore.Model.GioHang.ModelGioHang;
import com.example.beestore.Model.ObjectClass.SanPham;
import com.example.beestore.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterGioHang  extends RecyclerView.Adapter<AdapterGioHang.ViewHolderGioHang> {
    Context context;
    List<SanPham> sanPhamList ;
    ModelGioHang modelGioHang;

    SanPham sanPham = new SanPham();



    public AdapterGioHang(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        modelGioHang=new ModelGioHang();

        modelGioHang.MoKetNoiSQL(context);

    }

    public class ViewHolderGioHang extends RecyclerView.ViewHolder {
        TextView txtTenTieuDeGioHang,txtGiaTienGioHang,txtSoLuongSanPham,txtGiamGiaGioHang;
        ImageView imghinhGioHang,imgXoaSanPhamGioHang;
        ImageButton imgTangSoLuong,imgGiamSoLuong;

        public ViewHolderGioHang(@NonNull View itemView) {
            super(itemView);

            txtTenTieuDeGioHang=itemView.findViewById(R.id.txtTieuDeGioHang);
            txtGiaTienGioHang=itemView.findViewById(R.id.txtGiaGioHang);
            imghinhGioHang=itemView.findViewById(R.id.imgHinhGioHang);
            imgXoaSanPhamGioHang=itemView.findViewById(R.id.imXoaSanPhamGioHang);
            txtSoLuongSanPham=itemView.findViewById(R.id.txtSoLuongSanPham);
            imgTangSoLuong=itemView.findViewById(R.id.imTangSoLuongSPTrongGioHang);
            imgGiamSoLuong=itemView.findViewById(R.id.imGiamSoLuongSPTrongGioHang);
            txtGiamGiaGioHang=itemView.findViewById(R.id.txtGiamGiaGioHang);


        }
    }

    @NonNull
    @Override
    public ViewHolderGioHang onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.custom_layout_giohang,viewGroup,false);

        ViewHolderGioHang viewHolderGioHang=new ViewHolderGioHang(view);


        return viewHolderGioHang;


    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderGioHang viewHolderGioHang, final int i) {
              sanPham= sanPhamList.get(i);
              viewHolderGioHang.txtTenTieuDeGioHang.setText(sanPham.getTENSP());



              //xử lý giảm giá
        // -----------------------------------------------------------------------------
        int giatien=sanPham.getGIA();
        int phantram=49;

        viewHolderGioHang.txtGiamGiaGioHang.setVisibility(View.VISIBLE);
        viewHolderGioHang.txtGiamGiaGioHang.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolderGioHang.txtGiamGiaGioHang.setText(giatien +"VND");
        giatien=giatien*phantram/100;

        NumberFormat numberFormat=new DecimalFormat("###,###");
        String gia=numberFormat.format(giatien);
        viewHolderGioHang.txtGiaTienGioHang.setText(gia +"VND");





//---------------------------------------------------------------------


        byte[] hinhsanpham=sanPham.getHinhgiohang();

        Bitmap bitmapgiohang= BitmapFactory.decodeByteArray(hinhsanpham,0,hinhsanpham.length);
        viewHolderGioHang.imghinhGioHang.setImageBitmap(bitmapgiohang);

        viewHolderGioHang.imgXoaSanPhamGioHang.setTag(sanPham.getMASP());
        viewHolderGioHang.imgXoaSanPhamGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d("kiemtra",v.getTag()+"");

                ModelGioHang modelGioHang=new ModelGioHang();
                modelGioHang.MoKetNoiSQL(context);
                sanPhamList.remove(i);
                modelGioHang.XoaSanPhamTrongGioHang((int) v.getTag());
                notifyDataSetChanged();

            }
        });
        viewHolderGioHang.txtSoLuongSanPham.setText(String.valueOf(sanPham.getSOLUONG()));
        viewHolderGioHang.imgTangSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong= Integer.parseInt(viewHolderGioHang.txtSoLuongSanPham.getText().toString());
                int soluongtonkho=sanPham.getSOLUONGTONKHO();
                if (soluong<soluongtonkho)
                {
                    soluong++;
                }
                else
                {
                    Toast.makeText(context,"Số lượng sản phẩm bạn mua quá số lượng trong cửa hàng",Toast.LENGTH_SHORT).show();
                }

                modelGioHang.CapNhatSoLuongSanPhamGioHang(sanPham.getMASP(),soluong);
                viewHolderGioHang.txtSoLuongSanPham.setText(String.valueOf(soluong));
            }
        });
        viewHolderGioHang.imgGiamSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong= Integer.parseInt(viewHolderGioHang.txtSoLuongSanPham.getText().toString());
               if (soluong>1)
               {
                   soluong--;
               }

                viewHolderGioHang.txtSoLuongSanPham.setText(String.valueOf(soluong));
            }
        });


    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


}
