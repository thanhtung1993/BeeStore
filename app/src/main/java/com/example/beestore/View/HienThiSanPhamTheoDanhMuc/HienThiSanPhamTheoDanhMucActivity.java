package com.example.beestore.View.HienThiSanPhamTheoDanhMuc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.beestore.Adapter.AdapterTopDienThoaiDienTu;
import com.example.beestore.Model.ObjectClass.ILoadMore;
import com.example.beestore.Model.ObjectClass.LoadMoreScroll;
import com.example.beestore.Model.ObjectClass.SanPham;
import com.example.beestore.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.beestore.Presenter.HienThiSanPhamTheoDanhMuc.PresenterLogicHienThiSanPhamTheoDanhMuc;
import com.example.beestore.R;
import com.example.beestore.View.GioHang.GioHangActivity;

import java.util.List;


public class HienThiSanPhamTheoDanhMucActivity extends AppCompatActivity implements ViewHienThiSanPhamTheoDanhMuc, View.OnClickListener , ILoadMore {


RecyclerView recyclerView;
Button btnthaydoitrangthairecycler;
ProgressBar progressBar;
boolean danggrid=true;
RecyclerView.LayoutManager layoutManager;
PresenterLogicHienThiSanPhamTheoDanhMuc sanPhamTheoDanhMuc;
AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu;
int masp;
boolean kiemtra;
Toolbar toolbar;
List<SanPham> sanPhamList1;
TextView txtGioHang;
boolean onPause=true;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hienthisanphamtheodanhmuc);

        recyclerView=findViewById(R.id.recyclerViewHienThiSanPhamTheoDanhMuc);
        btnthaydoitrangthairecycler=findViewById(R.id.btnThayDoiTrangThaiRecycler);
        toolbar=findViewById(R.id.toolbar);
        progressBar=findViewById(R.id.progressbar);


          Intent intent=getIntent();
          masp=intent.getIntExtra("MALOAI",0);
          String tensanpham=intent.getStringExtra("TENLOAI");
          kiemtra=intent.getBooleanExtra("KIEMTRA",false);




        sanPhamTheoDanhMuc=new PresenterLogicHienThiSanPhamTheoDanhMuc(this);
        sanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp,kiemtra);


        btnthaydoitrangthairecycler.setOnClickListener(this);
        toolbar.setTitle(tensanpham);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu,menu);

        //tạo số lượng giỏ hàng
        MenuItem itGioHang=menu.findItem(R.id.itGioHang);
        View giaoDienCustomGioHang= MenuItemCompat.getActionView(itGioHang);
        txtGioHang=giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);
        PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));

        //Chuyển trang
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iThanhToan=new Intent(HienThiSanPhamTheoDanhMucActivity.this, GioHangActivity.class);
                startActivity(iThanhToan);
            }
        });



        return true;
    }





    @Override
    public void HienThiDanhSachSanPham(List<SanPham> sanPhamList) {
    //  adapterTopDienThoaiDienTu=new AdapterTopDienThoaiDienTu(HienThiSanPhamTheoDanhMucActivity.this,R.layout.custom_layout_topdienthoaivamaytinhbang,sanPhamList);
        sanPhamList1=sanPhamList;
        if (danggrid)
        {
             layoutManager=new GridLayoutManager(HienThiSanPhamTheoDanhMucActivity.this,2);
             adapterTopDienThoaiDienTu= new AdapterTopDienThoaiDienTu(HienThiSanPhamTheoDanhMucActivity.this,R.layout.custom_layout_topdienthoaivamaytinhbang,sanPhamList1);
        }
        else
        {
            layoutManager=new LinearLayoutManager(HienThiSanPhamTheoDanhMucActivity.this);
            adapterTopDienThoaiDienTu=  new AdapterTopDienThoaiDienTu(HienThiSanPhamTheoDanhMucActivity.this,R.layout.custom_layout_list_topdienthoaivamaytinhbang,sanPhamList1);

       }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopDienThoaiDienTu);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        adapterTopDienThoaiDienTu.notifyDataSetChanged();


    }

    @Override
    public void LoiHienThiDanhSachSanPham() {

    }

    @Override
    public void onClick(View v) {
        int id =v.getId();
        switch (id)
        {
            case R.id.btnThayDoiTrangThaiRecycler:
                danggrid =!danggrid;
                sanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp,kiemtra);


                break;
        }

    }

    @Override
    public void LoadMore(int tongitem) {
     List<SanPham> listSanPhamLoadMore=  sanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoaiLoadMorẹ(masp,kiemtra,tongitem,progressBar);
     sanPhamList1.addAll(listSanPhamLoadMore);
     adapterTopDienThoaiDienTu.notifyDataSetChanged();


    }
}
