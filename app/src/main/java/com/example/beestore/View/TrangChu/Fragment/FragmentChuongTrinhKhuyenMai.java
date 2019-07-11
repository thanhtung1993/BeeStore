package com.example.beestore.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.beestore.Adapter.AdapterDanhSachKhuyenMai;
import com.example.beestore.Model.ObjectClass.KhuyenMai;
import com.example.beestore.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.example.beestore.R;
import com.example.beestore.View.TrangChu.ViewKhuyenMai;
import com.squareup.picasso.Picasso;

import java.util.List;


public class FragmentChuongTrinhKhuyenMai extends Fragment implements ViewKhuyenMai {

    LinearLayout lnHinhKhuyenMai;
    RecyclerView recyclerView;
    PresenterLogicKhuyenMai presenterLogicKhuyenMai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.layout_chuongtrinhkhuyenmai,container,false);

       lnHinhKhuyenMai=view.findViewById(R.id.lnHinhKhuyenMai);
       recyclerView=view.findViewById(R.id.recylerDanhSachKhuyenMai);

       presenterLogicKhuyenMai = new PresenterLogicKhuyenMai(this);
       presenterLogicKhuyenMai.LayDanhSachKhuyenMai();




        return view;
    }

    @Override
    public void HienThiDanhSachKhuyenMai(List<KhuyenMai> khuyenMaiList) {
            int demhinh=khuyenMaiList.size();
            if (demhinh>5)
            {
                demhinh=5 ;
            }
            else
            {
                demhinh=khuyenMaiList.size();
            }

            lnHinhKhuyenMai.removeAllViews();
            for (int i=0;i<demhinh;i++)
            {
                ImageView imageView=new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.setMargins(0,10,0,10);
                imageView.setLayoutParams(layoutParams);

                Picasso.with(getContext()).load(khuyenMaiList.get(i).getHINHKHUYENMAI()).resize(720,200).into(imageView);
                lnHinhKhuyenMai.addView(imageView);

            }



        AdapterDanhSachKhuyenMai adapterDanhSachKhuyenMai=new AdapterDanhSachKhuyenMai(getContext(),khuyenMaiList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDanhSachKhuyenMai);
        adapterDanhSachKhuyenMai.notifyDataSetChanged();
    }
}
