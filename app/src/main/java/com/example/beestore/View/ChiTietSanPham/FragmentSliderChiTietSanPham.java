package com.example.beestore.View.ChiTietSanPham;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.beestore.R;
import com.squareup.picasso.Picasso;

public class FragmentSliderChiTietSanPham extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view=inflater.inflate(R.layout.layout_fragment_slider_chitietsanpham,container,false);
       Bundle bundle=getArguments();
       String linkhinh=bundle.getString("linkhinh");

        ImageView imageView=view.findViewById(R.id.imgHinhSlider);

        Picasso.with(getContext()).load(linkhinh).into(imageView);

        return view;


    }
}
