package com.example.beestore.View.DangNhap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.beestore.Adapter.ViewPagerAdapterDangNhap;
import com.example.beestore.R;


public class DangNhapActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);



        tabLayout=findViewById(R.id.tabDangNhap);
        viewPager=findViewById(R.id.viewPagerDangNhap);
        toolbar=findViewById(R.id.toolbarDangNhap);

        //cài toolbar
        setSupportActionBar(toolbar);

        ViewPagerAdapterDangNhap viewPagerAdapterDangNhap=new ViewPagerAdapterDangNhap(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapterDangNhap);
        viewPagerAdapterDangNhap.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);

    }
}
