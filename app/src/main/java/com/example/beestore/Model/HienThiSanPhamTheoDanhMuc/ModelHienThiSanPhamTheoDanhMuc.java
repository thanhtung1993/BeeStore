package com.example.beestore.Model.HienThiSanPhamTheoDanhMuc;



import com.example.beestore.Connectinternet.DownloadJSON;
import com.example.beestore.Model.ObjectClass.SanPham;
import com.example.beestore.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class   ModelHienThiSanPhamTheoDanhMuc {
    public List<SanPham> LayDanhSachSanPhamTheoMaLoai(int masp, String tenmang, String tenham, int limit)
    {
        List<SanPham> sanPhamList=new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON="";


        String duongdan= TrangChuActivity.SERVER_NAME;


        HashMap<String,String> hsham=new HashMap<>();
        hsham.put("ham",tenham);

        HashMap<String,String> hsMaLoai=new HashMap<>();
        hsMaLoai.put("maloaisp",String.valueOf(masp));

        HashMap<String,String> hslimit=new HashMap<>();
        hslimit.put("limit",String.valueOf(limit));

      attrs.add(hsham);
      attrs.add(hsMaLoai);
      attrs.add(hslimit);

        DownloadJSON downloadJSON=new DownloadJSON(duongdan,attrs);

        downloadJSON.execute();

        try
        {
            dataJSON=downloadJSON.get();
        //    Log.d("json",dataJSON);


            JSONObject jsonObject=new JSONObject(dataJSON);
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
                sanPham.setANHNHO(object.getString("HINHSANPHAMNHO"));

                sanPhamList.add(sanPham);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return sanPhamList;

    }

}
