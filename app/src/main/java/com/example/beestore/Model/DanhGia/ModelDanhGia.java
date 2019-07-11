package com.example.beestore.Model.DanhGia;


import com.example.beestore.Connectinternet.DownloadJSON;
import com.example.beestore.Model.ObjectClass.DanhGia;
import com.example.beestore.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.concurrent.ExecutionException;

public class ModelDanhGia {


    public List<DanhGia> LayDanhSachDanhGiaCuaSanPham(int masp, int limit) {
        List<DanhGia> danhGiaList = new ArrayList<>();

        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "LayDanhSachDanhGiaTheoMaSP");

        HashMap<String, String> hsMaLoai = new HashMap<>();
        hsMaLoai.put("masp", String.valueOf(masp));

        HashMap<String, String> hsLimit = new HashMap<>();
        hsLimit.put("limit", String.valueOf(limit));

        attrs.add(hsHam);
        attrs.add(hsMaLoai);
        attrs.add(hsLimit);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);

            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray("DANHSACHDANHGIA");

            int dem = jsonArrayDanhSachSanPham.length();

            for (int i = 0; i < dem; i++) {
                DanhGia danhGia = new DanhGia();
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);

                danhGia.setTENTHIETBI(object.getString("TENTHIETBI"));
                danhGia.setNOIDUNG(object.getString("NOIDUNG"));
                danhGia.setSOSAO(object.getInt("SOSAO"));
                danhGia.setMASP(object.getInt("MASP"));
                danhGia.setMADG(object.getString("MADG"));
                danhGia.setNGAYDG(object.getString("NGAYDANHGIA"));
                danhGia.setTIEUDE(object.getString("TIEUDE"));

                danhGiaList.add(danhGia);


            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
return danhGiaList;

    }

    public boolean ThemDanhGia(DanhGia danhGia)
    {
        String duongdan= TrangChuActivity.SERVER_NAME;
        boolean kiemtra=false;
        List<HashMap<String,String>> attrs=new ArrayList<>();

        HashMap<String,String> hsHam=new HashMap<>();
        hsHam.put("ham","ThemDanhGia");

      //  HashMap<String,String> hsMADG=new HashMap<>();
       // hsMADG.put("madg",danhGia.getMADG());

        HashMap<String,String> hsMaSP= new HashMap<>();
        hsMaSP.put("masp", String.valueOf(danhGia.getMASP()));

        HashMap<String,String> hsTieuDe=new HashMap<>();
        hsTieuDe.put("tieude",danhGia.getTIEUDE());

        HashMap<String,String> hsNoiDung=new HashMap<>();
        hsNoiDung.put("noidung",danhGia.getNOIDUNG());

        HashMap<String,String> hsSoSao=new HashMap<>();
        hsSoSao.put("sosao", String.valueOf(danhGia.getSOSAO()));

        HashMap<String,String> hsTenThietBi=new HashMap<>();
        hsTenThietBi.put("tenthietbi",danhGia.getTENTHIETBI());

        attrs.add(hsHam);
       // attrs.add(hsMADG);
        attrs.add(hsMaSP);
        attrs.add(hsTieuDe);
        attrs.add(hsNoiDung);
        attrs.add(hsSoSao);
        attrs.add(hsTenThietBi);

        DownloadJSON downloadJSON=new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieuJSON=downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String ketqua =jsonObject.getString("ketqua");
            if (ketqua.equals("true"))
            {
                kiemtra=true;
            }
            else
            {
                kiemtra=false;
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
