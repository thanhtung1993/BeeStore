package com.example.beestore.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.beestore.View.DangNhap.Fragment.FragmentDangKy;
import com.example.beestore.View.DangNhap.Fragment.FragmentDangNhap;


public class ViewPagerAdapterDangNhap  extends FragmentPagerAdapter {

    public ViewPagerAdapterDangNhap(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                FragmentDangNhap fragmentDangNhap=new FragmentDangNhap();
                return fragmentDangNhap;
            case 1:
                FragmentDangKy fragmentDangKy=new FragmentDangKy();
                return fragmentDangKy;
                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int i) {
        switch (i)
        {
            case 0:

                return "Đăng Nhập";
            case 1:
                return  "Đăng ký";
            default:
                return null;
        }


    }
}
