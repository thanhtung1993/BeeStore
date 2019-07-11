package com.example.beestore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beestore.Model.ObjectClass.KhuyenMai;
import com.example.beestore.R;

import java.util.List;


public class AdapterDanhSachKhuyenMai extends RecyclerView.Adapter<AdapterDanhSachKhuyenMai.ViewHolderKhuyenMai> {
    @NonNull
    @Override
    public ViewHolderKhuyenMai onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.custom_layout_itemkhuyenmai,viewGroup,false);
        return new ViewHolderKhuyenMai(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderKhuyenMai viewHolderKhuyenMai, int i) {

        KhuyenMai khuyenMai=khuyenMaiList.get(i);
        viewHolderKhuyenMai.txtTieuDeKhuyenMai.setText(khuyenMai.getTENLOAISP());
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu=new AdapterTopDienThoaiDienTu(context,R.layout.custom_layout_topdienthoaivamaytinhbang,khuyenMai.getDanhSachSanPhamKhuyenMai());

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        viewHolderKhuyenMai.recyclerView.setLayoutManager(layoutManager);
        viewHolderKhuyenMai.recyclerView.setAdapter(adapterTopDienThoaiDienTu);

    }

    @Override
    public int getItemCount() {
        return khuyenMaiList.size();
    }

    Context context;
    List<KhuyenMai> khuyenMaiList;

    public AdapterDanhSachKhuyenMai(Context context, List<KhuyenMai> khuyenMaiList) {
        this.context = context;
        this.khuyenMaiList = khuyenMaiList;
    }

    public class ViewHolderKhuyenMai extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        TextView txtTieuDeKhuyenMai;
        public ViewHolderKhuyenMai(@NonNull View itemView) {

            super(itemView);
            recyclerView=itemView.findViewById(R.id.recycleItemKhuyenMai);
            txtTieuDeKhuyenMai=itemView.findViewById(R.id.txtTieuDeKhuyenMai);
        }
    }
}
