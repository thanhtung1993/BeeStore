package com.example.beestore.Presenter.ChiTietSanPham;

import android.content.Context;


import com.example.beestore.Model.ObjectClass.SanPham;

public interface IPresenterChiTietSanPham {
    void LayChiTietSanPham(int masp);
    void LayDanhSachDanhGiaCuaSanPham(int masp, int limit);
    void ThemGioHang(SanPham sanPham, Context context);



}
