package com.example.beestore.Presenter.ChiTietSanPham;

import android.content.Context;

import com.example.beestore.Model.ChiTietSanPham.ModelChiTietSanPham;
import com.example.beestore.Model.GioHang.ModelGioHang;
import com.example.beestore.Model.ObjectClass.DanhGia;
import com.example.beestore.Model.ObjectClass.SanPham;
import com.example.beestore.View.ChiTietSanPham.ViewChiTietSanPham;

import java.util.List;


public class PresenterLogicChiTietSanPham implements IPresenterChiTietSanPham{
    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;
    ModelGioHang modelGioHang;

    public PresenterLogicChiTietSanPham()
    {
       modelGioHang=new ModelGioHang();
    }
    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelChiTietSanPham=new ModelChiTietSanPham();
        modelGioHang=new ModelGioHang();
    }

    @Override
    public void LayChiTietSanPham(int masp) {
        SanPham sanPham=modelChiTietSanPham.LayChiTietSanPham("LaySanPhamVsChiTietTheoMaSP","CHITIETSANPHAM",masp);
        if (sanPham.getMASP()>0)
{
    String[] linkhinhanh=sanPham.getANHNHO().split(",");//cắt chuỗi
    viewChiTietSanPham.HienThiSliderSanPham(linkhinhanh);

    //Kích hoạt phương thức hiển thị sản phẩm
    viewChiTietSanPham.HienThiChiTietSanPham(sanPham);
}

    }

    @Override
    public void LayDanhSachDanhGiaCuaSanPham(int masp, int limit) {
        List<DanhGia> danhGias=modelChiTietSanPham.LayDanhSachDanhGiaCuaSanPham(masp,limit);
        if (danhGias.size()>0)
        {
            viewChiTietSanPham.HienThiDanhGia(danhGias);
        }


    }

    @Override
    public void ThemGioHang(SanPham sanPham, Context context) {
        modelGioHang.MoKetNoiSQL(context);
        boolean kiemtra= modelGioHang.ThemGioHang(sanPham);

        if (kiemtra) {
        viewChiTietSanPham.ThemGioHangThanhCong();
        }
        else
        {
          viewChiTietSanPham.ThemGioHangThatbai();
        }


    }
    public int DemSanPhamCoTrongGioHang(Context context)
    {
        modelGioHang.MoKetNoiSQL(context);
      List<SanPham> sanPhamList=  modelGioHang.LayDanhSachSanPhamTrongGioHang();
      int count=sanPhamList.size();
      return count;

    }
}
