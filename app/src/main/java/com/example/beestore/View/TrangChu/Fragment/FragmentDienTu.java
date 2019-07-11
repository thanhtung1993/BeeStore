package com.example.beestore.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.beestore.Adapter.AdapterDienTu;
import com.example.beestore.Adapter.AdapterThuongHieuLonDienTu;
import com.example.beestore.Adapter.AdapterTopDienThoaiDienTu;
import com.example.beestore.Model.ObjectClass.DienTu;
import com.example.beestore.Model.ObjectClass.SanPham;
import com.example.beestore.Model.ObjectClass.ThuongHieu;
import com.example.beestore.Presenter.TrangChu_DienTu.PresenterLogicDienTu;
import com.example.beestore.R;
import com.example.beestore.View.TrangChu.ViewDienTu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentDienTu extends Fragment implements ViewDienTu {

    RecyclerView recyclerView,recylerTopCacThuongHieuLon,recyclerHangMoiVe; ;
    List<DienTu> dienTuList;
    PresenterLogicDienTu presenterLogicDienTu;
    ImageView imgSanPham1,imgSanPham2,imgSanPham3;
    TextView txtSanPham1,txtSanPham2,txtSanPham3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_dientu,container,false);

        recyclerView=view.findViewById(R.id.recyclerDienTu);
        recylerTopCacThuongHieuLon=view.findViewById(R.id.recylerTopCacThuongHieuLon);
        recyclerHangMoiVe = view.findViewById(R.id.recylerHangMoiVe );

        imgSanPham1=view.findViewById(R.id.imSanPham1);
        imgSanPham2=view.findViewById(R.id.imSanPham2);
        imgSanPham3=view.findViewById(R.id.imSanPham3);

        txtSanPham1=view.findViewById(R.id.txtSanPham1);
        txtSanPham2=view.findViewById(R.id.txtSanPham2);
        txtSanPham3=view.findViewById(R.id.txtSanPham3);

        presenterLogicDienTu = new PresenterLogicDienTu(this);

        dienTuList=new ArrayList<>();
        presenterLogicDienTu.LayDanhSachDienTu();
        presenterLogicDienTu.LayDanhSachLogoThuongHieu();
        presenterLogicDienTu.LayDanhSachSanPhamMoi();

        return view;
    }

    @Override
    public void HienThiDanhSach(List<DienTu> dienTus) {

           dienTuList=dienTus;



        AdapterDienTu adapterDienTu=new AdapterDienTu(getActivity(),dienTuList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDienTu);
        adapterDienTu.notifyDataSetChanged();




    }

    @Override
    public void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieus) {
        AdapterThuongHieuLonDienTu adapterThuongHieuLonDienTu=new AdapterThuongHieuLonDienTu(getContext(),thuongHieus);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.HORIZONTAL,false);

       recylerTopCacThuongHieuLon.setLayoutManager(layoutManager);
       recylerTopCacThuongHieuLon.setAdapter(adapterThuongHieuLonDienTu);
       adapterThuongHieuLonDienTu.notifyDataSetChanged();
    }

    @Override
    public void LoiLayDuLieu() {

    }

    @Override
    public void HienThiSanPhamMoiVe(List<SanPham> sanPhams) {
      AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu=new AdapterTopDienThoaiDienTu(getContext(),R.layout.custom_layout_topdienthoaivamaytinhbang,sanPhams);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

         recyclerHangMoiVe.setLayoutManager(layoutManager);
         recyclerHangMoiVe.setAdapter(adapterTopDienThoaiDienTu);
         adapterTopDienThoaiDienTu.notifyDataSetChanged();

        Random random=new Random();
        int vitri=random.nextInt(sanPhams.size());

        Picasso.with(getContext()).load(sanPhams.get(vitri).getANHLON()).fit().centerInside().into(imgSanPham1);
        txtSanPham1.setText(sanPhams.get(vitri).getTENSP());

        Random random1=new Random();
        int vitri1=random1.nextInt(sanPhams.size());
        Picasso.with(getContext()).load(sanPhams.get(vitri1).getANHLON()).fit().centerInside().into(imgSanPham2);
        txtSanPham2.setText(sanPhams.get(vitri1).getTENSP());

        Random random2=new Random();
        int vitri2=random2.nextInt(sanPhams.size());
        Picasso.with(getContext()).load(sanPhams.get(vitri2).getANHLON()).fit().centerInside().into(imgSanPham3);
        txtSanPham3.setText(sanPhams.get(vitri2).getTENSP());

    }

}
