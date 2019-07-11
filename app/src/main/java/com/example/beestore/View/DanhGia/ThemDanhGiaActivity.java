package com.example.beestore.View.DanhGia;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;


import com.example.beestore.Model.ObjectClass.DanhGia;
import com.example.beestore.Presenter.DanhGia.PresenterLogicDanhGia;
import com.example.beestore.R;

import java.util.List;

public class ThemDanhGiaActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener, ViewDanhGia {
    TextInputLayout input_edtTieuDe, input_edtNoiDung;
    EditText edtTieuDe, edtNoiDung;
    RatingBar ratingBar;
    Button btnDongYDanhGia;
    int masp = 0;
    int sosao = 0;
    PresenterLogicDanhGia presenterLogicDanhGia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themdanhgia);


        input_edtNoiDung = findViewById(R.id.input_edtNoiDungDanhGia);
        input_edtTieuDe = findViewById(R.id.input_edtNoiDungDanhGia);
        edtNoiDung = findViewById(R.id.edtNoiDung);
        edtTieuDe = findViewById(R.id.edTieuDe);
        ratingBar = findViewById(R.id.rbDanhGia);
        btnDongYDanhGia = findViewById(R.id.btnDongYDanhGia);

        masp = getIntent().getIntExtra("masp", 0);

        presenterLogicDanhGia = new PresenterLogicDanhGia(this);

        ratingBar.setOnRatingBarChangeListener(this);
        btnDongYDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenthietbi = Build.MODEL;
                String tieude = edtTieuDe.getText().toString();
                String noidung = edtNoiDung.getText().toString();

                if (tieude.trim().length() > 0) {
                    input_edtTieuDe.setErrorEnabled(false);
                    input_edtTieuDe.setError("");
                } else {
                    input_edtTieuDe.setErrorEnabled(true);
                    input_edtTieuDe.setError("Bạn chưa nhập tiêu đề !");
                }

                if (noidung.trim().length() > 0) {
                    input_edtNoiDung.setError("");
                    input_edtNoiDung.setErrorEnabled(false);
                } else {
                    input_edtNoiDung.setErrorEnabled(true);
                    input_edtNoiDung.setError("Bạn chưa nhập nội dung");
                }

                if (!input_edtNoiDung.isErrorEnabled() && !input_edtTieuDe.isErrorEnabled()) {
                    DanhGia danhGia = new DanhGia();
                    danhGia.setMASP(masp);
                    danhGia.setTIEUDE(tieude);
                    danhGia.setNOIDUNG(noidung);
                    danhGia.setSOSAO(sosao);
                    danhGia.setTENTHIETBI(tenthietbi);
                    presenterLogicDanhGia.ThemDanhGia(danhGia);
                    finish();

                }

            }
        });


    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        sosao = (int) rating;
    }

    @Override
    public void DanhGiaThanhCong() {
        Toast.makeText(this, "Đánh Giá Thành Công", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void DanhGiaThatBai() {
        Toast.makeText(this, "Bạn không thể đánh giá sản phẩm này", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void HienThiDanhGiaTheoSanPham(List<DanhGia> danhGiaList) {

    }


}




