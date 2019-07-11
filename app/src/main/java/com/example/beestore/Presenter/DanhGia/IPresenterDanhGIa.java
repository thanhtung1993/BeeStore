package com.example.beestore.Presenter.DanhGia;

import android.widget.ProgressBar;

import com.example.beestore.Model.ObjectClass.DanhGia;


public interface IPresenterDanhGIa {
    void ThemDanhGia(DanhGia danhGia);
    void LayDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar);
}
