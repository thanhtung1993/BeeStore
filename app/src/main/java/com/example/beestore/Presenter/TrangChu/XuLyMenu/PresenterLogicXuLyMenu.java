package com.example.beestore.Presenter.TrangChu.XuLyMenu;

import android.util.Log;


import com.example.beestore.Connectinternet.DownloadJSON;
import com.example.beestore.Model.DangNhap.ModelDangNhap;
import com.example.beestore.Model.ObjectClass.LoaiSanPham;
import com.example.beestore.Model.TrangChu.XuLyMenu.XuLyJSONMenu;
import com.example.beestore.View.TrangChu.TrangChuActivity;
import com.example.beestore.View.TrangChu.ViewXuLyMenu;
import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu {
    ViewXuLyMenu viewXuLyMenu;
    String tennguoidung="";

    public PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu) {
        this.viewXuLyMenu = viewXuLyMenu;
    }

    @Override
    public void LayDanhSachMenu() {
        List<LoaiSanPham> loaiSanPhamList;
        String dataJson = "";
        //phuong thức get
        /*String duongdan="http://192.168.1.170:8080/weblazada/loaisanpham.php?maloaicha=0";
        DownloadJSON downloadJSON=new DownloadJSON(duongdan);
        downloadJSON.execute();*/

        //Phương thức post là truyền ngầm
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsham = new HashMap<>();
        hsham.put("ham", "LayDanhSachMenu");

        HashMap<String, String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha","0");
        attrs.add(hsMaLoaiCha);
        attrs.add(hsham);


        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();


        try {
            dataJson = downloadJSON.get();
            Log.d("dulieu", dataJson);
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu(duongdan);
            loaiSanPhamList = xuLyJSONMenu.ParserJSONMenu(dataJson);
            viewXuLyMenu.HienThiDanhSachMenu(loaiSanPhamList);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public AccessToken LayTenNguoiDungFB() {
        ModelDangNhap modelDangNhap = new ModelDangNhap();
        AccessToken accessToken = modelDangNhap.LayTokenFacebookHienTai();

      //  XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();

      // String tennguoidung = xuLyJSONMenu.LayTenNguoiDungFB(accessToken);



        return accessToken;

    }
}
