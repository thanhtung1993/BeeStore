package com.example.beestore.Model.DangNhap;



import com.example.beestore.Connectinternet.DownloadJSON;
import com.example.beestore.Model.ObjectClass.NhanVien;
import com.example.beestore.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDangKy {
    public Boolean DangKyThanhVien(NhanVien nhanVien)
    {
        String duongdan= TrangChuActivity.SERVER_NAME+"/webbeestore/loaisanpham.php";
        Boolean kiemtra=false;
        List<HashMap<String,String>> attrs=new ArrayList<>();

        HashMap<String,String> hsham=new HashMap<>();
        hsham.put("ham","DangKyThanhVien");

        HashMap<String,String> hsTenNV=new HashMap<>();
        hsTenNV.put("tennv",nhanVien.getTenNV());

        HashMap<String,String> hsTenDN=new HashMap<>();
        hsTenDN.put("tendangnhap",nhanVien.getTenDN());

        HashMap<String,String> hsMatKhau=new HashMap<>();
        hsMatKhau.put("matkhau",nhanVien.getMatKhau());

        HashMap<String,String> hsMaLoaiNV=new HashMap<>();
        hsMaLoaiNV.put("maloainv",String.valueOf(nhanVien.getMaLoaiNV()));

        HashMap<String,String> hsEmailDocQuyen=new HashMap<>();
        hsEmailDocQuyen.put("emaildocquyen",nhanVien.getEmailDocQuyen());

        attrs.add(hsham);
        attrs.add(hsTenNV);
        attrs.add(hsTenDN);
        attrs.add(hsMatKhau);
        attrs.add(hsMaLoaiNV);
        attrs.add(hsEmailDocQuyen);

        DownloadJSON downloadJSON=new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieu=downloadJSON.get();
            JSONObject jsonObject=new JSONObject(dulieu);
            String ketqua=jsonObject.getString("ketqua");
         //   Log.d("kiemtra",ketqua);
            if (ketqua.equals("true")) {
              kiemtra= true;

            }
            else
            {
                kiemtra= false;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return kiemtra;
    }
}
