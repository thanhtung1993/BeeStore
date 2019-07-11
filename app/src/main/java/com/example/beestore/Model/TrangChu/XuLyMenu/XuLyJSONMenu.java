package com.example.beestore.Model.TrangChu.XuLyMenu;

import android.util.Log;


import com.example.beestore.Connectinternet.DownloadJSON;
import com.example.beestore.Model.ObjectClass.LoaiSanPham;
import com.example.beestore.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class  XuLyJSONMenu {
    String dulieujson;
    String tennguoidung="";
    public XuLyJSONMenu(String dulieujson)
    {
        this.dulieujson=dulieujson;
    }

    public XuLyJSONMenu() {

    }

    public List<LoaiSanPham> ParserJSONMenu(String dulieujson)
    {
        List<LoaiSanPham> list=new ArrayList<>();
        try {

            JSONObject jsonObject = new JSONObject(dulieujson);
            JSONArray loaisanpham=jsonObject.getJSONArray("LOAISANPHAM");
            int count=loaisanpham.length();

            for (int i=0;i<count;i++)
            {
                JSONObject value=loaisanpham.getJSONObject(i);
               // Log.d("menu",dulieujson);

                LoaiSanPham dataloaiSanPham=new LoaiSanPham();
                dataloaiSanPham.setMALOAISP(Integer.parseInt(value.getString("MALOAISP")));
                dataloaiSanPham.setMALOAICHA(Integer.parseInt(value.getString("MALOAICHA")));
                dataloaiSanPham.setTENLOAISP((value.getString("TENLOAISP")));

                list.add(dataloaiSanPham);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }


public List<LoaiSanPham> LayLoaiSanPhamTheoMaLoai(int maloaisp)
{
    List<LoaiSanPham> loaiSanPhamList=new ArrayList<>();
    //dowload dữ liệu từ web

    List<HashMap<String ,String>> attrs=new ArrayList<>();
    String dataJson="";


    String duongdan= TrangChuActivity.SERVER_NAME ;

    HashMap<String,String> hsHam=new HashMap<>();
    hsHam.put("ham","LayDanhSachMenu");

    HashMap<String,String> hsMaLoaiCha=new HashMap<>();
    hsMaLoaiCha.put("maloaicha",String.valueOf(maloaisp));
    attrs.add(hsMaLoaiCha);
    attrs.add(hsHam);


    DownloadJSON downloadJSON=new DownloadJSON(duongdan,attrs);
    downloadJSON.execute();

    try {
        dataJson=downloadJSON.get();
        Log.d("dulieu",dataJson);
        XuLyJSONMenu xuLyJSONMenu=new XuLyJSONMenu(duongdan);
        loaiSanPhamList= xuLyJSONMenu.ParserJSONMenu(dataJson);


    } catch (ExecutionException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    return loaiSanPhamList;


}


}
