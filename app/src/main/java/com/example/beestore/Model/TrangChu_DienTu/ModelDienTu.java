package com.example.beestore.Model.TrangChu_DienTu;


import com.example.beestore.Connectinternet.DownloadJSON;
import com.example.beestore.Model.ObjectClass.SanPham;
import com.example.beestore.Model.ObjectClass.ThuongHieu;
import com.example.beestore.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDienTu {





    public List<SanPham> LayDanhSachSanPhamTop(String tenham, String tenmang)
    {
        List<SanPham> sanPhams=new ArrayList<>();

        List<HashMap<String,String>> attrs=new ArrayList<>();
        String dataJson="";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsham =new HashMap<>();
        hsham.put("ham",tenham);

        attrs.add(hsham);

        DownloadJSON downloadJSON=new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJson=downloadJSON.get();

            JSONObject jsonObject=new JSONObject(dataJson);
            JSONArray jsonArrayDanhSachSanPham=jsonObject.getJSONArray(tenmang);
            int count=jsonArrayDanhSachSanPham.length();
            for (int i=0;i<count;i++)
            {
                SanPham sanPham=new SanPham();
                JSONObject object=jsonArrayDanhSachSanPham.getJSONObject(i);

                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHLON(object.getString("HINHSANPHAM"));

                sanPhams.add(sanPham);

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return sanPhams ;

    }



    public List<ThuongHieu> LayDanhSachThuongHieuLon(String tenham, String tenmang)
    {
        List<ThuongHieu> thuongHieuList=new ArrayList<>();

        List<HashMap<String,String>> attrs=new ArrayList<>();
        String dataJson="";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsham =new HashMap<>();
        hsham.put("ham",tenham);

        attrs.add(hsham);

        DownloadJSON downloadJSON=new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJson=downloadJSON.get();

            JSONObject jsonObject=new JSONObject(dataJson);
            JSONArray jsonArrayDanhSachThuongHieu=jsonObject.getJSONArray(tenmang);
            int count=jsonArrayDanhSachThuongHieu.length();
            for (int i=0;i<count;i++)
            {
                ThuongHieu thuongHieu=new ThuongHieu();
                JSONObject object=jsonArrayDanhSachThuongHieu.getJSONObject(i);

                thuongHieu.setMATHUONGHIEU(object.getInt("MASP"));
                thuongHieu.setTENTHUONGHIEU(object.getString("TENSP"));
                thuongHieu.setHINHTHUONGHIEU(object.getString("HINHSANPHAM"));

                thuongHieuList.add(thuongHieu);

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return thuongHieuList ;
    }
}
