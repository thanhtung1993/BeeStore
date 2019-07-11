package com.example.beestore.Model.ChiTietSanPham;

import android.util.Log;


import com.example.beestore.Connectinternet.DownloadJSON;
import com.example.beestore.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.beestore.Model.ObjectClass.ChiTietSanPham;
import com.example.beestore.Model.ObjectClass.DanhGia;
import com.example.beestore.Model.ObjectClass.SanPham;
import com.example.beestore.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelChiTietSanPham {

    public List<DanhGia> LayDanhSachDanhGiaCuaSanPham(int masp, int limit)
    {
           List<DanhGia> danhGias=new ArrayList<>();
           List<HashMap<String,String>> attrs   =new ArrayList<>();
           String dataJSON="";
           String duongdan= TrangChuActivity.SERVER_NAME;

           HashMap<String,String> hsHam=new HashMap<>();
           hsHam.put("ham","LayDanhSachDanhGiaTheoMaSP");

           HashMap<String,String> hsMaLoai=new HashMap<>();
           hsMaLoai.put("masp", String.valueOf(masp));

           HashMap<String,String> hsLimit=new HashMap<>();
           hsLimit.put("limit", String.valueOf(limit));

           attrs.add(hsHam);
           attrs.add(hsMaLoai);
           attrs.add(hsLimit);


           DownloadJSON downloadJSON=new DownloadJSON(duongdan,attrs);
           downloadJSON.execute();


        try {
            dataJSON=downloadJSON.get();
            JSONObject jsonObject=new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachDanhGia=jsonObject.getJSONArray("DANHSACHDANHGIA");
            int count=jsonArrayDanhSachDanhGia.length();

            for (int i=0;i<count;i++)
            {
                DanhGia danhGia=new DanhGia();
                JSONObject object=jsonArrayDanhSachDanhGia.getJSONObject(i);

                danhGia.setTENTHIETBI(object.getString("TENTHIETBI"));
                danhGia.setNOIDUNG(object.getString("NOIDUNG"));
                danhGia.setSOSAO(object.getInt("SOSAO"));
                danhGia.setMASP(object.getInt("MASP"));
                danhGia.setMADG(object.getString("MADG"));
                danhGia.setNGAYDG(object.getString("NGAYDANHGIA"));
                danhGia.setTIEUDE(object.getString("TIEUDE"));

                danhGias.add(danhGia);




            }
        } catch (ExecutionException e) {

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return danhGias;
    }


    public SanPham LayChiTietSanPham(String tenham, String tenmang, int masp)
    {
        SanPham sanPham=new SanPham();
        List<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();

        List<HashMap<String,String>> attrs=new ArrayList<>();
        String dataJson="";

        String duongdan= TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam= new HashMap<>();
        hsHam.put("ham",tenham);

        HashMap<String,String> hsMasp=new HashMap<>();
        hsMasp.put("masp", String.valueOf(masp));

        attrs.add(hsHam);
        attrs.add(hsMasp);

        DownloadJSON downloadJSON=new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJson=downloadJSON.get();
             Log.d("kiemtra",dataJson.toString());

            JSONObject jsonObject=new JSONObject(dataJson);
            JSONArray jsonArrayDanhSachSanPham=jsonObject.getJSONArray(tenmang);
            int count=jsonArrayDanhSachSanPham.length();
            for (int i=0;i<count;i++)
            {
                JSONObject object=jsonArrayDanhSachSanPham.getJSONObject(i);
                //thêm km
                ChiTietKhuyenMai chiTietKhuyenMai=new ChiTietKhuyenMai();
                chiTietKhuyenMai.setPHANTRAMKM(object.getInt("PHANTRAMKM"));
                sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);

                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHNHO(object.getString("ANHNHO"));
                sanPham.setSOLUONG(object.getInt("SOLUONG"));
                sanPham.setTHONGTIN(object.getString("THONGTIN"));
                sanPham.setMALOAISP(object.getInt("MALOAISP"));
                sanPham.setMATHUONGHIEU(object.getInt("MATHUONGHIEU"));
                sanPham.setMANV(object.getInt("MANV"));
                sanPham.setTENNV(object.getString("TENNV"));
                sanPham.setLUOTMUA(object.getInt("LUOTMUA"));

                JSONArray jsonArrayThongSoKyThuat=object.getJSONArray("THONGSOKYTHUAT");
                for(int j=0;j<jsonArrayThongSoKyThuat.length();j++)
                {
                    JSONObject jsonObject1=jsonArrayThongSoKyThuat.getJSONObject(j);
                    for (int k=0;k<jsonObject1.names().length();j++)
                    {
                        String tenchitiet=jsonObject1.names().getString(k);
                        ChiTietSanPham chiTietSanPham=new ChiTietSanPham();
                        chiTietSanPham.setTENCHITIET(tenchitiet);
                        chiTietSanPham.setGIATRI(jsonObject1.getString(tenchitiet));
                        chiTietSanPhams.add(chiTietSanPham);
                    }
                }
                sanPham.setChiTietSanPhamList(chiTietSanPhams);

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sanPham;
    }

}
