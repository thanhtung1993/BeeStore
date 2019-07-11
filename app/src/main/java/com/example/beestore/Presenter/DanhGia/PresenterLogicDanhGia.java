package com.example.beestore.Presenter.DanhGia;

import android.view.View;
import android.widget.ProgressBar;

import com.example.beestore.Model.DanhGia.ModelDanhGia;
import com.example.beestore.Model.ObjectClass.DanhGia;
import com.example.beestore.View.DanhGia.ViewDanhGia;

import java.util.List;


public class  PresenterLogicDanhGia implements IPresenterDanhGIa{
    ViewDanhGia viewDanhGia;
    ModelDanhGia modelDanhGia;
  //phuowng thuwcsk howir taoj
    public PresenterLogicDanhGia(ViewDanhGia viewDanhGia) {
        this.viewDanhGia = viewDanhGia;
        modelDanhGia=new ModelDanhGia();
    }

    @Override
    public void ThemDanhGia(DanhGia danhGia) {
        boolean kiemtra=modelDanhGia.ThemDanhGia(danhGia);
       if (kiemtra)
       {
           viewDanhGia.DanhGiaThanhCong();
       }
       else
       {
           viewDanhGia.DanhGiaThatBai();
       }

    }

    @Override
    public void LayDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar) {
          progressBar.setVisibility(View.VISIBLE);
        List<DanhGia> danhGiaList=modelDanhGia.LayDanhSachDanhGiaCuaSanPham(masp,limit);

    if (danhGiaList.size()>0)
    {
        progressBar.setVisibility(View.GONE);
        viewDanhGia.HienThiDanhGiaTheoSanPham( danhGiaList);
    }

    }
}
