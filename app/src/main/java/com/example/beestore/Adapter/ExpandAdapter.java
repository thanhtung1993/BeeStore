package com.example.beestore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;


import com.example.beestore.Model.ObjectClass.LoaiSanPham;
import com.example.beestore.Model.TrangChu.XuLyMenu.XuLyJSONMenu;
import com.example.beestore.R;

import java.util.List;

public class ExpandAdapter extends BaseExpandableListAdapter {

    Context context;
    List<LoaiSanPham> loaiSanPhams;
    public ExpandAdapter(Context context, List<LoaiSanPham> loaiSanPhams)
    {
        this.context=context;
        this.loaiSanPhams=loaiSanPhams;
        //tạo menucon

        XuLyJSONMenu xuLyJSONMenu=new XuLyJSONMenu();
        int count=loaiSanPhams.size();
        for (int i=0;i<count;i++)
        {
            int maloaisp=loaiSanPhams.get(i).getMALOAISP();
            loaiSanPhams.get(i).setLoaiSanPhamcon(xuLyJSONMenu.LayLoaiSanPhamTheoMaLoai(maloaisp));
        }



    }
    @Override
    public int getGroupCount() {//đếm thằng cha
        return loaiSanPhams.size();
    }

    @Override
    public int getChildrenCount(int vitriGroupCha) {
        return loaiSanPhams.get(vitriGroupCha).getLoaiSanPhamcon().size();
    }

    @Override
    public Object getGroup(int vitriGroupCha) {
        return loaiSanPhams.get(vitriGroupCha);
    }

    @Override
    public Object getChild(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhams.get(vitriGroupCha).getLoaiSanPhamcon().get(vitriGroupCon);
    }

    @Override
    public long getGroupId(int vitriGroupCha) {
        return loaiSanPhams.get(vitriGroupCha).getMALOAISP();
    }

    @Override
    public long getChildId(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhams.get(vitriGroupCha).getLoaiSanPhamcon().get(vitriGroupCon).getMALOAISP();

    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int vitriGroupCha, boolean isExpanded, View view, ViewGroup viewGroup) {
        //tạo layout
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewGroupCha =layoutInflater.inflate(R.layout.custom_layout_group_cha,viewGroup,false);

        //tìm bên giao diện
        TextView txttemloaisanpham=viewGroupCha.findViewById(R.id.txttenloaisanpham);
        //lấy dữ liệu
        txttemloaisanpham.setText(loaiSanPhams.get(vitriGroupCha).getTENLOAISP());
        return viewGroupCha;
    }

    @Override
    public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean isExpanded, View view, ViewGroup viewGroup) {
        //tạo layout
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewGroupCha =layoutInflater.inflate(R.layout.custom_layout_group_cha,viewGroup,false);

        //tìm bên giao diện
        TextView txttemloaisanpham=viewGroupCha.findViewById(R.id.txttenloaisanpham);
        //lấy dữ liệu
        txttemloaisanpham.setText(loaiSanPhams.get(vitriGroupCha).getLoaiSanPhamcon().get(vitriGroupCon).getTENLOAISP());
        return viewGroupCha;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
  class SecondAdapter extends BaseExpandableListAdapter
{
    List<LoaiSanPham> listCon;
    Context context;

    public SecondAdapter(List<LoaiSanPham> listCon)
    {
        this.listCon=listCon;
        XuLyJSONMenu xuLyJSONMenu=new XuLyJSONMenu();
        int count=listCon.size();
        for (int i=0;i<count;i++)
        {
            int maloaisp=listCon.get(i).getMALOAISP();
            listCon.get(i).setLoaiSanPhamcon(xuLyJSONMenu.LayLoaiSanPhamTheoMaLoai(maloaisp));
        }
    }

    @Override
    public int getGroupCount() {
        return listCon.size();
    }

    @Override
    public int getChildrenCount(int vitriGroupCha) {
        return listCon.get(vitriGroupCha).getLoaiSanPhamcon().size();
    }

    @Override
    public Object getGroup(int vitriGroupCha) {
        return listCon.get(vitriGroupCha);
    }

    @Override
    public Object getChild(int vitriGroupCha, int vitriGroupCon) {
        return listCon.get(vitriGroupCha).getLoaiSanPhamcon().get(vitriGroupCon);
    }

    @Override
    public long getGroupId(int vitriGroupCha) {
        return listCon.get(vitriGroupCha).getMALOAISP();
    }

    @Override
    public long getChildId(int vitriGroupCha, int vitriGroupCon) {
        return listCon.get(vitriGroupCha).getLoaiSanPhamcon().get(vitriGroupCon).getMALOAISP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int vitriGroupCha, boolean isExpanded, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewGroupCha=inflater.inflate(R.layout.custom_layout_group_cha,viewGroup,false);
        TextView txtTenLoaiSP=viewGroupCha.findViewById(R.id.txttenloaisanpham);
        txtTenLoaiSP.setText(listCon.get(vitriGroupCha).getTENLOAISP());


        return viewGroupCha;
    }

    @Override
    public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean isExpanded, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewGroupcon=inflater.inflate(R.layout.custom_layout_group_con,viewGroup,false);

         ExpandableListView expandableListView=viewGroupcon.findViewById(R.id.epMenuCon);
        SecondAdapter secondAdapter=new SecondAdapter(listCon.get(vitriGroupCha).getLoaiSanPhamcon());
        expandableListView.setAdapter(secondAdapter);
        notifyDataSetChanged();



        return viewGroupcon;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
