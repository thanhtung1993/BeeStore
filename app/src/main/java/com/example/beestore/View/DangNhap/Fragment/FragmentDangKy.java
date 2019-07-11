package com.example.beestore.View.DangNhap.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beestore.Model.ObjectClass.NhanVien;
import com.example.beestore.Presenter.DangKy.PresenterLogicDangKy;
import com.example.beestore.R;
import com.example.beestore.View.DangNhap.ViewDangKy;


public class FragmentDangKy extends Fragment implements ViewDangKy,View.OnClickListener, View.OnFocusChangeListener {

    PresenterLogicDangKy presenterLogicDangKy;
    Button btnDangKy;
    EditText edtHoTen,edtMatKhau,edtNhapLaiMK,edtEmail;
    SwitchCompat sEmailDocQuyen;
    TextInputLayout input_edtHoTen,input_edtMatKhau,input_edtNhapLaiMK,input_edtEmail;
    Boolean kiemtrathongtin=false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    //tạo view
        View view=inflater.inflate(R.layout.layout_fragment_dangky,container,false);

        btnDangKy=view.findViewById(R.id.btnDangKy);
        edtHoTen=view.findViewById(R.id.edtHoTenDK);
        edtMatKhau=view.findViewById(R.id.edtMatKhauDK);
        edtNhapLaiMK=view.findViewById(R.id.edtNhapLaiMK);
        sEmailDocQuyen=view.findViewById(R.id.sEmailDocQuyen);
        edtEmail=view.findViewById(R.id.edtEmail);
        input_edtHoTen=view.findViewById(R.id.input_edtHoTen);
        input_edtMatKhau=view.findViewById(R.id.input_edtMatKhauDK);
        input_edtNhapLaiMK=view.findViewById(R.id.input_edtNhapLaiMK);
        input_edtEmail=view.findViewById(R.id.input_edtDiaChiEmail);



        presenterLogicDangKy = new PresenterLogicDangKy(this);
        btnDangKy.setOnClickListener(this);
        edtHoTen.setOnFocusChangeListener(this);

        edtNhapLaiMK.setOnFocusChangeListener(this);
        edtEmail.setOnFocusChangeListener(this);



        return view;
    }

    @Override
    public void DangKyThanhCong() {
        Toast.makeText(getActivity(),"Đăng ký thành công !",Toast.LENGTH_SHORT).show();


    }

    @Override
    public void DangKyThatBai() {
        Toast.makeText(getActivity(),"Đăng ký thất bại !",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.btnDangKy:
               btnDangKy();


                break;
        }

    }
    String emaildocquyen="";
    private void btnDangKy()
    {
        String hoten=edtHoTen.getText().toString();
        String email=edtEmail.getText().toString();
        String matkhau=edtMatKhau.getText().toString();
        String nhaplaimk=edtNhapLaiMK.getText().toString();

        sEmailDocQuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                emaildocquyen= isChecked+"";
            }
        });

if (kiemtrathongtin)
{
    NhanVien nhanVien = new NhanVien();
    nhanVien.setTenNV(hoten);
    nhanVien.setTenDN(email);
    nhanVien.setMatKhau(matkhau);
    nhanVien.setEmailDocQuyen(emaildocquyen);
    nhanVien.setMaLoaiNV(2);
    presenterLogicDangKy.ThucHienDangKy(nhanVien);
}
else
{
   // Log.d("kiemtra","Dang ky that bai ");
}

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
      int id=v.getId();
      switch (id)
      {
          case R.id.edtHoTenDK:
              if (!hasFocus)
              {
                  String chuoi=((EditText)v).getText().toString();
                  if (chuoi.trim().equals("") || chuoi.equals(null)){
                      input_edtHoTen.setErrorEnabled(true);
                      input_edtHoTen.setError("Bạn chưa nhập mục này");
                      kiemtrathongtin=false;

                  }
                  else
                  {
                      input_edtHoTen.setErrorEnabled(false);
                      input_edtHoTen.setError("");
                      kiemtrathongtin=true;
                  }
              }

              break;
          case R.id.edtEmail:

            if (!hasFocus)
            {

                  String chuoi=((EditText)v).getText().toString();


                  if (chuoi.trim().equals("") || chuoi.equals(null)){
                      input_edtEmail.setErrorEnabled(true);
                      input_edtEmail.setError("Bạn chưa nhập mục này");
                      kiemtrathongtin=false;

                  }
                  else {
                      boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                      if (!kiemtraemail) {
                          input_edtEmail.setErrorEnabled(true);
                          input_edtEmail.setError("Đây không phải là địa chỉ email của bạn");
                      kiemtrathongtin=false;
                      } else {
                          input_edtEmail.setErrorEnabled(false);
                          input_edtEmail.setError("");
                          kiemtrathongtin=true;

                      }

                  }
              }

              break;
          case R.id.edtMatKhauDK:

              break;
          case R.id.edtNhapLaiMK:
              if (!hasFocus)
              {
                  String chuoi = ((EditText) v).getText().toString();
                  String matkhau = edtMatKhau.getText().toString();
                  if (!chuoi.equalsIgnoreCase(matkhau)) {
                      input_edtNhapLaiMK.setErrorEnabled(true);
                      input_edtNhapLaiMK.setError("Mật khẩu không trùng khớp");
                      kiemtrathongtin=false;
                  } else {
                      input_edtNhapLaiMK.setErrorEnabled(false);
                      input_edtNhapLaiMK.setError("");
                      kiemtrathongtin=true;

                  }
              }
              break;
      }
    }

}
