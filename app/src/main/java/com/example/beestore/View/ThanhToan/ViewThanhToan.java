package com.example.beestore.View.ThanhToan;


import com.example.beestore.Model.ObjectClass.SanPham;

import java.util.List;

public interface ViewThanhToan {
    void DatHangThanhCong();
    void DatHangThatBai();
    void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList);
}
