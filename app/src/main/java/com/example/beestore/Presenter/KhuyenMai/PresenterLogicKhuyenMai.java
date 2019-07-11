package com.example.beestore.Presenter.KhuyenMai;


import com.example.beestore.Model.KhuyenMai.ModelKhuyenMai;
import com.example.beestore.Model.ObjectClass.KhuyenMai;
import com.example.beestore.View.TrangChu.ViewKhuyenMai;

import java.util.List;

public class PresenterLogicKhuyenMai implements IPresenterKhuyenMai {
    ViewKhuyenMai viewKhuyenMai;
    ModelKhuyenMai modelKhuyenMai;
    public PresenterLogicKhuyenMai(ViewKhuyenMai viewKhuyenMai) {
        this.viewKhuyenMai = viewKhuyenMai;
        modelKhuyenMai=new ModelKhuyenMai();
    }




    @Override
    public void LayDanhSachKhuyenMai() {
        List<KhuyenMai> khuyenMaiList=modelKhuyenMai.LayDanhSachSanPhamTheoMaiLoai("LayDanhSachKhuyenMai","DANHSACHKHUYENMAI");
        if (khuyenMaiList.size()>0)
        {
            viewKhuyenMai.HienThiDanhSachKhuyenMai(khuyenMaiList);

        }
    }
}
