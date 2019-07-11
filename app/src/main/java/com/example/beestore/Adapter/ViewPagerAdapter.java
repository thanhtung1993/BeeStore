package com.example.beestore.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.beestore.View.TrangChu.Fragment.FragmentChuongTrinhKhuyenMai;
import com.example.beestore.View.TrangChu.Fragment.FragmentDienTu;
import com.example.beestore.View.TrangChu.Fragment.FragmentLamDep;
import com.example.beestore.View.TrangChu.Fragment.FragmentMeVaBe;
import com.example.beestore.View.TrangChu.Fragment.FragmentNhaCuaVaDoiSong;
import com.example.beestore.View.TrangChu.Fragment.FragmentNoiBat;
import com.example.beestore.View.TrangChu.Fragment.FragmentTheThaoVaDuLich;
import com.example.beestore.View.TrangChu.Fragment.FragmentThoiTrang;
import com.example.beestore.View.TrangChu.Fragment.FragmentThuongHieu;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> list=new ArrayList<Fragment>();
    List<String> titlefragment= new ArrayList<String>();
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        list.add(new FragmentNoiBat());
        list.add(new FragmentChuongTrinhKhuyenMai());
        list.add(new FragmentDienTu());
        list.add(new FragmentNhaCuaVaDoiSong());
        list.add(new FragmentMeVaBe());
        list.add(new FragmentLamDep());
        list.add(new FragmentThoiTrang());
        list.add(new FragmentTheThaoVaDuLich());
        list.add(new FragmentThuongHieu());

        titlefragment.add("Nổi bật");
        titlefragment.add("Chương trình khuyến mãi");
        titlefragment.add("Điện Tử");
        titlefragment.add("Nhà cửa & đời sống");
        titlefragment.add("Mẹ và bé");
        titlefragment.add("Làm đẹp ");
        titlefragment.add("Thời trang");
        titlefragment.add("Thể thao và du lịch");
        titlefragment.add("Thương hiệu");
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlefragment.get(position);//trả ra title của fragment
    }
}
