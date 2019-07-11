package com.example.beestore.View.TrangChu;


import com.example.beestore.Model.ObjectClass.DienTu;
import com.example.beestore.Model.ObjectClass.SanPham;
import com.example.beestore.Model.ObjectClass.ThuongHieu;

import java.util.List;

public interface ViewDienTu {
    void HienThiDanhSach(List<DienTu> dienTus);
    void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieus);
    void LoiLayDuLieu();
    void HienThiSanPhamMoiVe(List<SanPham> sanPhams);


}
