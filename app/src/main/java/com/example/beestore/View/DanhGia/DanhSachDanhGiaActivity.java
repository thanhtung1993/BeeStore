package com.example.beestore.View.DanhGia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;


import com.example.beestore.Adapter.AdapterDanhGia;
import com.example.beestore.Model.ObjectClass.DanhGia;
import com.example.beestore.Model.ObjectClass.ILoadMore;
import com.example.beestore.Model.ObjectClass.LoadMoreScroll;
import com.example.beestore.Presenter.DanhGia.PresenterLogicDanhGia;
import com.example.beestore.R;

import java.util.ArrayList;
import java.util.List;

public class DanhSachDanhGiaActivity  extends AppCompatActivity implements ViewDanhGia, ILoadMore {

    RecyclerView recyclerViewDanhSachDanhGia;
    ProgressBar progressBar;
    PresenterLogicDanhGia presenterLogicDanhGia;
    int masp=0;
    List<DanhGia> tatCaDanhGia;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhsachdanhgia);

        recyclerViewDanhSachDanhGia=findViewById(R.id.recycleDanhSachDanhGia);
        progressBar=findViewById(R.id.progressbar);

        masp=getIntent().getIntExtra("masp",0);
        tatCaDanhGia=new ArrayList<>();

        presenterLogicDanhGia=new PresenterLogicDanhGia(this);
        presenterLogicDanhGia.LayDanhSachDanhGiaCuaSanPham(masp,0,progressBar);




    }

    @Override
    public void DanhGiaThanhCong() {

    }

    @Override
    public void DanhGiaThatBai() {

    }

    @Override
    public void HienThiDanhGiaTheoSanPham(List<DanhGia> danhGiaList) {
        tatCaDanhGia.addAll(danhGiaList);

        AdapterDanhGia adapterDanhGia=new AdapterDanhGia(this,tatCaDanhGia,0);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerViewDanhSachDanhGia.setLayoutManager(layoutManager);
        recyclerViewDanhSachDanhGia.setAdapter(adapterDanhGia);
        recyclerViewDanhSachDanhGia.addOnScrollListener(new LoadMoreScroll(layoutManager,this));

        adapterDanhGia.notifyDataSetChanged();

    }


    @Override
    public void LoadMore(int tongitem) {
      presenterLogicDanhGia.LayDanhSachDanhGiaCuaSanPham(masp,tongitem,progressBar);
    }
}
