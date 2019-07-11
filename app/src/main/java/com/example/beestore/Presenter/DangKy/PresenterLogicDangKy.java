package com.example.beestore.Presenter.DangKy;


import com.example.beestore.Model.DangNhap.ModelDangKy;
import com.example.beestore.Model.ObjectClass.NhanVien;
import com.example.beestore.View.DangNhap.ViewDangKy;

public class PresenterLogicDangKy implements IPresenterDangKy{

    ViewDangKy viewDangKy;
    ModelDangKy modelDangKy;
    public PresenterLogicDangKy(ViewDangKy viewDangKy)
    {
        this.viewDangKy=viewDangKy;
        modelDangKy=new ModelDangKy();
    }
    @Override
    public void ThucHienDangKy(NhanVien nhanvien) {
      boolean kiemtra=  modelDangKy.DangKyThanhVien(nhanvien);
      if (kiemtra)
      {
          viewDangKy.DangKyThanhCong();
      }
      else
      {
          viewDangKy.DangKyThatBai();
      }

    }
}
