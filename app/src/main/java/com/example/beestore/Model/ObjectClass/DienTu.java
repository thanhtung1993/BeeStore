package com.example.beestore.Model.ObjectClass;

import java.util.List;

public class DienTu {
    List<ThuongHieu> thuongHieus;
    List<SanPham> sanPhams;
    boolean thuonghieu;

    public boolean isThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(boolean thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    String HinhSanPham;
    String tennoibat,tentopnoibat;

    public String getTennoibat() {
        return tennoibat;
    }

    public void setTennoibat(String tennoibat) {
        this.tennoibat = tennoibat;
    }

    public String getTentopnoibat() {
        return tentopnoibat;
    }

    public void setTentopnoibat(String tentopnoibat) {
        this.tentopnoibat = tentopnoibat;
    }

    public String getHinhSanPham() {
        return HinhSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        HinhSanPham = hinhSanPham;
    }

    public List<ThuongHieu> getThuongHieus() {
        return thuongHieus;
    }

    public void setThuongHieus(List<ThuongHieu> thuongHieus) {
        this.thuongHieus = thuongHieus;
    }

    public List<SanPham> getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(List<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }
}
