package com.example.beestore.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.beestore.Adapter.AdapterThuongHieuLon;
import com.example.beestore.Model.ObjectClass.DienTu;
import com.example.beestore.Model.ObjectClass.SanPham;
import com.example.beestore.Model.ObjectClass.ThuongHieu;
import com.example.beestore.Presenter.TrangChu_DienTu.PresenterLogicDienTu;
import com.example.beestore.R;
import com.example.beestore.View.TrangChu.ViewDienTu;

import java.util.ArrayList;
import java.util.List;


public class FragmentNoiBat extends Fragment implements ViewDienTu {
    RecyclerView recyclerView;
    List<DienTu> dienTuList;
    PresenterLogicDienTu presenterLogicDienTu;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_noibat, container, false);
        recyclerView = view.findViewById(R.id.RecyclerNoiBat);

        dienTuList=new ArrayList<>();
        presenterLogicDienTu = new PresenterLogicDienTu(this);
        presenterLogicDienTu.LayDanhSachLogoThuongHieu();
        return view;
    }


    @Override
    public void HienThiDanhSach(List<DienTu> dienTus) {

    }

    @Override
    public void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieus) {
        AdapterThuongHieuLon adapterThuongHieuLonDienTu=new AdapterThuongHieuLon(getContext(),thuongHieus,true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),4, GridLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterThuongHieuLonDienTu);
        adapterThuongHieuLonDienTu.notifyDataSetChanged();
    }

    @Override
    public void LoiLayDuLieu() {

    }

    @Override
    public void HienThiSanPhamMoiVe(List<SanPham> sanPhams) {

    }
}
