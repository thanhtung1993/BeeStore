package com.example.beestore.Model.ObjectClass;

import java.util.List;

public class LoaiSanPham {
    int MALOAISP,MALOAICHA;
    String TENLOAISP;
    List<LoaiSanPham> loaiSanPhamcon;

    public int getMALOAISP() {
        return MALOAISP;
    }

    public void setMALOAISP(int MALOAISP) {
        this.MALOAISP = MALOAISP;
    }

    public int getMALOAICHA() {
        return MALOAICHA;
    }

    public void setMALOAICHA(int MALOAICHA) {
        this.MALOAICHA = MALOAICHA;
    }

    public String getTENLOAISP() {
        return TENLOAISP;
    }

    public void setTENLOAISP(String TENLOAISP) {
        this.TENLOAISP = TENLOAISP;
    }

    public List<LoaiSanPham> getLoaiSanPhamcon() {
        return loaiSanPhamcon;
    }

    public void setLoaiSanPhamcon(List<LoaiSanPham> loaiSanPhamcon) {
        this.loaiSanPhamcon = loaiSanPhamcon;
    }
}
