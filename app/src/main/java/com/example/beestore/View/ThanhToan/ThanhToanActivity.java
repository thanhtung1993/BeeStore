package com.example.beestore.View.ThanhToan;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


import com.example.beestore.Model.ObjectClass.ChiTietHoaDon;
import com.example.beestore.Model.ObjectClass.HoaDon;
import com.example.beestore.Model.ObjectClass.SanPham;
import com.example.beestore.Presenter.ThanhToan.PresenterLogicThanhToan;
import com.example.beestore.R;
import com.example.beestore.View.TrangChu.TrangChuActivity;

import java.util.ArrayList;
import java.util.List;


public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener,ViewThanhToan{
    Toolbar toolbar;
    EditText edtTenNguoiNhan,edtDiaChi,edtSoDienThoai;
    ImageButton imgTraTienKhiGiaoHang,imgChuyenKhoan;
    Button btnThanhToan,btnQuayVe;
    CheckBox cbThoaThuan;
    List<ChiTietHoaDon> chiTietHoaDons=new ArrayList<>();
    PresenterLogicThanhToan presenterLogicThanhToan;
    TextView txtNhanTienKhiGiaoHang,txtChuyenKhoan;

    String chonHinhThuc="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanhtoan);

        toolbar=findViewById(R.id.toolbar);
        edtTenNguoiNhan=findViewById(R.id.edTenNguoiNhan);
        edtDiaChi=findViewById(R.id.edDiaChi);
        edtSoDienThoai=findViewById(R.id.edSoDT);
        imgTraTienKhiGiaoHang=findViewById(R.id.imNhanTienKhiGiaoHang);
        imgChuyenKhoan=findViewById(R.id.imChuyenKhoan);
        cbThoaThuan=findViewById(R.id.cbThoaThuan);
        btnThanhToan=findViewById(R.id.btnThanhToan);
        btnQuayVe=findViewById(R.id.btnQuayVe);
        txtChuyenKhoan=findViewById(R.id.txtChuyenKhoan);
        txtNhanTienKhiGiaoHang=findViewById(R.id.txtNhanTienKhiGiaoHang);


        btnThanhToan.setOnClickListener(this);
        btnQuayVe.setOnClickListener(this);
        presenterLogicThanhToan=new PresenterLogicThanhToan(this,this);
        presenterLogicThanhToan.LayDanhSachSanPhamTrongGioHang(this);
        imgChuyenKhoan.setOnClickListener(this);
        imgTraTienKhiGiaoHang.setOnClickListener(this);



        setSupportActionBar(toolbar);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.btnThanhToan:

                String tennguoinhan=edtTenNguoiNhan.getText().toString();
                String sodienthoai=edtSoDienThoai.getText().toString();
                String diachi=edtDiaChi.getText().toString();

                if (tennguoinhan.trim().length()>0 && sodienthoai.trim().length()>0 && diachi.trim().length()>0)
                {
                    if (cbThoaThuan.isChecked()){
                        HoaDon hoaDon=new HoaDon();
                        hoaDon.setTenNguoiNhan(tennguoinhan);
                        hoaDon.setSoDT(sodienthoai);
                        hoaDon.setDiaChi(diachi);
                        hoaDon.setChuyenKhoan(chonHinhThuc);
                        hoaDon.setChiTietHoaDonList(chiTietHoaDons);

                        presenterLogicThanhToan.ThemHoaDon(hoaDon);





                    }
                    else
                    {
                        Toast.makeText(this,"Bạn chưa nhấn chọn vào ô thỏa thuận !", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this,"Bạn chưa nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                }


                Toast.makeText(this,"Thành Công", Toast.LENGTH_SHORT).show();
                break;

            case R.id.imNhanTienKhiGiaoHang:
               ChonHinhThucGiaoHang(txtNhanTienKhiGiaoHang,txtChuyenKhoan);
               chonHinhThuc="COD";

                break;
            case R.id.imChuyenKhoan:
                ChonHinhThucGiaoHang(txtChuyenKhoan,txtNhanTienKhiGiaoHang);
                chonHinhThuc="Đã chuyển khoản !";
                break;
            case R.id.btnQuayVe:
                Intent itrangchu=new Intent(ThanhToanActivity.this, TrangChuActivity.class);
                startActivity(itrangchu);
                break;
        }


    }
    private void ChonHinhThucGiaoHang(TextView txtDuocChon,TextView txtHuyChon)
    {
        txtDuocChon.setTextColor(getIdColor(R.color.colorFacebook));
        txtHuyChon.setTextColor(getIdColor(R.color.colorBlack));
    }
    private int getIdColor(int idcolor)
    {
        int color=0;
        if (Build.VERSION.SDK_INT>21)
        {
            color= ContextCompat.getColor(this,idcolor);
        }
        else
        {
            color=getResources().getColor(idcolor);
        }
        return color;
    }

    @Override
    public void DatHangThanhCong() {
        Toast.makeText(this,"Thành công !",Toast.LENGTH_SHORT).show();
        Intent itrangchu=new Intent(ThanhToanActivity.this,TrangChuActivity.class);
        startActivity(itrangchu);

    }

    @Override
    public void DatHangThatBai() {
        Toast.makeText(this," Thất bại !",Toast.LENGTH_SHORT).show();


    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {
      chiTietHoaDons=new ArrayList<>();
        for (int i=0;i<sanPhamList.size();i++)
        {
            ChiTietHoaDon chiTietHoaDon=new ChiTietHoaDon();
            chiTietHoaDon.setMaSP(sanPhamList.get(i).getMASP());
            chiTietHoaDon.setSoLuong(sanPhamList.get(i).getSOLUONG());

            chiTietHoaDons.add(chiTietHoaDon);
        }

    }
}
