package com.example.beestore.View.GioHang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


import com.example.beestore.Adapter.AdapterGioHang;
import com.example.beestore.Model.ObjectClass.SanPham;
import com.example.beestore.Presenter.GioHang.PresenterLogicGioHang;
import com.example.beestore.R;
import com.example.beestore.View.ThanhToan.ThanhToanActivity;

import java.util.List;


public class GioHangActivity extends AppCompatActivity  implements ViewGioHang, View.OnClickListener {
    RecyclerView recyclerView;
    PresenterLogicGioHang presenterLogicGioHang;
    Toolbar toolbar;
    Button btnMuaNgay;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);



        recyclerView=findViewById(R.id.recyclerGioHang);
        btnMuaNgay=findViewById(R.id.btnMuaNgay);




        setSupportActionBar(toolbar);
        presenterLogicGioHang=new PresenterLogicGioHang(this);
        presenterLogicGioHang.LayDanhSachSanPhamTronGioHang(this);


        btnMuaNgay.setOnClickListener(this);



    }

    @Override
    public void HienThiDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        AdapterGioHang adapterGioHang=new AdapterGioHang(this,sanPhamList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterGioHang);
        adapterGioHang.notifyDataSetChanged();




    }



    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.btnMuaNgay:
                Intent iThanhToan=new Intent(GioHangActivity.this, ThanhToanActivity.class);
                startActivity(iThanhToan);

                break;
        }

    }
}
