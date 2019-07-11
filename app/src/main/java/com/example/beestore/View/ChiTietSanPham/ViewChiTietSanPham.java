package com.example.beestore.View.ChiTietSanPham;


import com.example.beestore.Model.ObjectClass.DanhGia;
import com.example.beestore.Model.ObjectClass.SanPham;

import java.util.List;

public interface ViewChiTietSanPham {
    void HienThiChiTietSanPham(SanPham sanPham);
    void HienThiSliderSanPham(String[] linkhinhsanpham);
    void HienThiDanhGia(List<DanhGia> danhGiaList);
    void ThemGioHangThanhCong();
    void ThemGioHangThatbai();
}
