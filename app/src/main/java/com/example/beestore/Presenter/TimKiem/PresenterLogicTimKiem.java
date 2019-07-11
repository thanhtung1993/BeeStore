package com.example.beestore.Presenter.TimKiem;


import com.example.beestore.Model.ObjectClass.SanPham;
import com.example.beestore.Model.TimKiem.ModelTimKiem;
import com.example.beestore.View.TimKiem.ViewTimKiem;

import java.util.List;

public class PresenterLogicTimKiem implements IPresenterTimKiem{

    ViewTimKiem viewTimKiem;
    ModelTimKiem modelTimKiem;

    public PresenterLogicTimKiem(ViewTimKiem viewTimKiem) {
        this.viewTimKiem = viewTimKiem;
        modelTimKiem=new ModelTimKiem();
    }

    @Override
    public void TimKiemSanPhamTheoTenSanPham(String tensp, int limit) {
        List<SanPham> sanPhamList=modelTimKiem.TimKiemSanPhamTheoTen(tensp,"DANHSACHSANPHAM","TimKiemSanPhamTheoTenSP",limit) ;

        if (sanPhamList.size()>0)
        {
            viewTimKiem.TimKiemThanhCong(sanPhamList);
        }
        else
        {
            viewTimKiem.TimKiemThatBai();
        }

    }
}
