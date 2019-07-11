package com.example.beestore.Presenter.GioHang;

import android.content.Context;

import com.example.beestore.Model.GioHang.ModelGioHang;
import com.example.beestore.Model.ObjectClass.SanPham;
import com.example.beestore.View.GioHang.ViewGioHang;

import java.util.List;


public class PresenterLogicGioHang implements IPresenterGioHang{

    ModelGioHang modelGioHang;
    ViewGioHang viewGioHang;
    public PresenterLogicGioHang(ViewGioHang viewGioHang)
    {
        modelGioHang=new ModelGioHang();
        this.viewGioHang=viewGioHang;
    }
    @Override
    public void LayDanhSachSanPhamTronGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> sanPhamList=modelGioHang.LayDanhSachSanPhamTrongGioHang();
        if (sanPhamList.size()>0)
        {
           viewGioHang.HienThiDanhSachSanPhamTrongGioHang(sanPhamList);
        }
    }
}
