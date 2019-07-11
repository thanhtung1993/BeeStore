package com.example.beestore.Presenter.TrangChu_DienTu;


import com.example.beestore.Model.ObjectClass.DienTu;
import com.example.beestore.Model.ObjectClass.SanPham;
import com.example.beestore.Model.ObjectClass.ThuongHieu;
import com.example.beestore.Model.TrangChu_DienTu.ModelDienTu;
import com.example.beestore.View.TrangChu.ViewDienTu;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicDienTu implements IPresenterDienTu {
    ViewDienTu viewDienTu;
    ModelDienTu modelDienTu;
    public PresenterLogicDienTu(ViewDienTu viewDienTu)
    {
        this.viewDienTu=viewDienTu;
        modelDienTu=new ModelDienTu();
    }


    @Override
    public void LayDanhSachDienTu() {

        List<DienTu> dienTus=new ArrayList<>();


      List<ThuongHieu> thuongHieuList= modelDienTu.LayDanhSachThuongHieuLon("LayDanhSachCacThuongHieuLon","DANHSACHTHUONGHIEU");
      List<SanPham> sanPhamList=modelDienTu.LayDanhSachSanPhamTop("LayDanhSachTopDienThoaiVaMayTinhBang","TOPDIENTHOAI&MAYTINHBANG");


        DienTu dienTu = new DienTu();
        dienTu.setThuongHieus(thuongHieuList);
        dienTu.setSanPhams(sanPhamList);
        dienTu.setTennoibat("Thương hiệu lớn");
        dienTu.setTentopnoibat("Top điện thoại và máy tính bảng");
        dienTu.setThuonghieu(true);
        dienTus.add(dienTu);

        List<SanPham> phukienList = modelDienTu.LayDanhSachSanPhamTop("LayDanhSachTopPhuKien","TOPPHUKIEN");
        List<ThuongHieu> topphukienList = modelDienTu.LayDanhSachThuongHieuLon("LayDanhSachPhuKien","DANHSACHPHUKIEN");


        DienTu dienTu1 = new DienTu();
        dienTu1.setThuongHieus(topphukienList);
        dienTu1.setSanPhams(phukienList);
        dienTu1.setTennoibat("Phụ kiện");
        dienTu1.setTentopnoibat("Top phụ kiện");
        dienTu1.setThuonghieu(false);
        dienTus.add(dienTu1);


        List<SanPham> tienichList = modelDienTu.LayDanhSachSanPhamTop("LayTopTienIch","TOPTIENICH");
        List<ThuongHieu> toptienichList = modelDienTu.LayDanhSachThuongHieuLon("LayDanhSachTienIch","DANHSACHTIENICH");

        DienTu dienTu2 = new DienTu();
        dienTu2.setThuongHieus(toptienichList);
        dienTu2.setSanPhams(tienichList);
        dienTu2.setTennoibat("Tiện ích");
        dienTu2.setTentopnoibat("Top Video & Tivi");
        dienTu2.setThuonghieu(false);
        dienTus.add(dienTu2);
        if (thuongHieuList.size()>0 )
      {
          viewDienTu.HienThiDanhSach(dienTus);
      }



    }

    @Override
    public void LayDanhSachLogoThuongHieu() {
        List<ThuongHieu> thuongHieuList =modelDienTu.LayDanhSachThuongHieuLon("LayLogoThuongHieuLon","DANHSACHTHUONGHIEU");
        if (thuongHieuList.size()>0) {
            viewDienTu.HienThiLogoThuongHieu(thuongHieuList);

        }
        else
        {
            viewDienTu.LoiLayDuLieu();
        }
    }

    @Override
    public void LayDanhSachSanPhamMoi() {
       List<SanPham> sanPhamList =modelDienTu.LayDanhSachSanPhamTop("LayDanhSachSanPhamMoi","DANHSACHSANPHAMMOIVE");
        if (sanPhamList.size()>0) {
          viewDienTu.HienThiSanPhamMoiVe(sanPhamList);

        }
        else
        {
            viewDienTu.LoiLayDuLieu();

        }

    }
}
