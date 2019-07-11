package com.example.beestore.View.ChiTietSanPham;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.beestore.Adapter.AdapterDanhGia;
import com.example.beestore.Adapter.AdapterViewPagerSlider;
import com.example.beestore.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.beestore.Model.ObjectClass.ChiTietSanPham;
import com.example.beestore.Model.ObjectClass.DanhGia;
import com.example.beestore.Model.ObjectClass.SanPham;
import com.example.beestore.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.beestore.R;
import com.example.beestore.View.DanhGia.DanhSachDanhGiaActivity;
import com.example.beestore.View.DanhGia.ThemDanhGiaActivity;
import com.example.beestore.View.GioHang.GioHangActivity;
import com.example.beestore.View.ThanhToan.ThanhToanActivity;
import com.example.beestore.View.TrangChu.TrangChuActivity;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity  extends AppCompatActivity implements ViewChiTietSanPham, ViewPager.OnPageChangeListener , View.OnClickListener {
     ViewPager viewPager;
     TextView[] txtDots;
     LinearLayout layoutdots;
     PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
     int masp=0;
     List<Fragment> fragmentList;
     Button btnMuaNgay;

     TextView txtTenSanPham,txtGiaTien,txtGioHang,txtCuaHangDongGoi,txtThongTinChiTiet,txtVietDanhGia,txtXemChiTietDanhGia,txtGiamGia;
     ImageView imgXemThemChiTiet,imgThemGioHang;
     Toolbar toolbar;
     Boolean kiemtraxochitiet=false;
     LinearLayout lnThongSoKyThuat;
     RecyclerView recyclerView;
     SanPham sanPhamGioHang;
     boolean onPause=true;






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);


         viewPager=findViewById(R.id.viewpagerSlider);
         layoutdots=findViewById(R.id.layoutDots);
         txtTenSanPham=findViewById(R.id.txtTenSanPham);
         txtGiaTien=findViewById(R.id.txtGiaTien);
         toolbar=findViewById(R.id.toolbar);
         txtCuaHangDongGoi=findViewById(R.id.txtTenCHDongGoi);
         txtThongTinChiTiet=findViewById(R.id.txtThongTinChiTiet);
         imgXemThemChiTiet=findViewById(R.id.imgXemThemChiTiet);
         lnThongSoKyThuat=findViewById(R.id.lnThongSoKyThuat);
         txtVietDanhGia=findViewById(R.id.txtVietDanhGia);
         txtXemChiTietDanhGia=findViewById(R.id.txtXemTatCaNhanXet);
         imgThemGioHang=findViewById(R.id.imgThemGioHang);
         btnMuaNgay=findViewById(R.id.btnMuaNgay);
         txtGiamGia=findViewById(R.id.txtGiamGia);


         recyclerView=findViewById(R.id.recycleDanhGiaChiTiet);

         //khởi tạo tootlbar
         setSupportActionBar(toolbar);


         masp=getIntent().getIntExtra("masp",0);

         presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
         presenterLogicChiTietSanPham.LayChiTietSanPham(masp);
         presenterLogicChiTietSanPham.LayDanhSachDanhGiaCuaSanPham(masp,0);

        //đăng kí sự kiện cho viết đánh giá
        txtVietDanhGia.setOnClickListener(this);
        txtXemChiTietDanhGia.setOnClickListener(this);
        imgThemGioHang.setOnClickListener(this);
        btnMuaNgay.setOnClickListener(this);


    }

    //Khởi tạo menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu,menu);
        //
        MenuItem itGioHang=menu.findItem(R.id.itGioHang);
        View giaoDienCustomGioHang= MenuItemCompat.getActionView(itGioHang);
        txtGioHang=giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));

        //chuyển trang giohang
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang=new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });
        return true;
    }

public void XuLyGiamGia(SanPham sanPham)
{

    int giatien=sanPham.getGIA();
    ChiTietKhuyenMai chiTietKhuyenMai=sanPham.getChiTietKhuyenMai();
    if (chiTietKhuyenMai !=null)
    {


        int phamtramkm=chiTietKhuyenMai.getPHANTRAMKM();
        if (phamtramkm !=0)
        {
            NumberFormat numberFormat=new DecimalFormat("###,###");
            String gia=numberFormat.format(giatien);

            txtGiamGia.setVisibility(View.VISIBLE);
            txtGiamGia.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            txtGiamGia.setText(gia+"VNĐ");
            giatien = giatien * phamtramkm / 100;

        }
        //xử lý giá tiền
        NumberFormat numberFormat=new DecimalFormat("###,###");
        String gia=numberFormat.format(giatien);
        txtGiaTien.setText(gia+" VNĐ");


    }

}
    @Override
    public void HienThiChiTietSanPham(final SanPham sanPham) {
        masp=sanPham.getMASP();
        txtTenSanPham.setText(sanPham.getTENSP());
        sanPhamGioHang=sanPham;
        sanPhamGioHang.setSOLUONGTONKHO(sanPham.getSOLUONG());
       // String[] linkhinh=sanPham.getANHNHO().split(",");
        //tạo giá km
//-------------------------------------------------------------------------------------------------

XuLyGiamGia(sanPham);




        txtCuaHangDongGoi.setText(sanPham.getTENNV());
        txtThongTinChiTiet.setText(sanPham.getTHONGTIN().substring(0,100));

        if (sanPham.getTHONGTIN().length()<100)
        {
            imgXemThemChiTiet.setVisibility(View.GONE);
        }
        else
        {
            imgXemThemChiTiet.setVisibility(View.VISIBLE);

            imgXemThemChiTiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
            kiemtraxochitiet=!kiemtraxochitiet;
            if (kiemtraxochitiet)
            {

                //sau khi xổ chi tiết
                txtThongTinChiTiet.setText(sanPham.getTHONGTIN());
                imgXemThemChiTiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_keyboard_arrow_up_black_24dp));
                lnThongSoKyThuat.setVisibility(View.VISIBLE);

               // HienThiThongSoKyThuat(sanPham);



            }
            else
            {
                //sau khi đóng chi tiết
                txtThongTinChiTiet.setText(sanPham.getTHONGTIN().substring(0,100));
                imgXemThemChiTiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_keyboard_arrow_down_black_24dp));
                lnThongSoKyThuat.setVisibility(View.GONE);
            }
                }
            });
        }




    }

List<ChiTietSanPham> chiTietSanPhams=new ArrayList<>();

 private void HienThiThongSoKyThuat(SanPham sanPham) {


        chiTietSanPhams = sanPham.getChiTietSanPhamList();

        for (int i = 0; i <chiTietSanPhams.size() ; i++) {

            //Truyền động layout
            LinearLayout lnChiTiet = new LinearLayout(this);
            lnChiTiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lnChiTiet.setOrientation(LinearLayout.HORIZONTAL);


            TextView txtTenThongSo = new TextView(this);
            txtTenThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            txtTenThongSo.setText(sanPham.getChiTietSanPhamList().get(i).getTENCHITIET());
             //txtTenThongSo.setText(chiTietSanPham.getTENCHITIET());

            TextView txtGiaTriThongSo = new TextView(this);
            txtGiaTriThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            txtGiaTriThongSo.setText(sanPham.getChiTietSanPhamList().get(i).getGIATRI());
           // txtGiaTriThongSo.setText(chiTietSanPham.getGIATRI());

            lnChiTiet.addView(txtTenThongSo);
            lnChiTiet.addView(txtGiaTriThongSo);

            lnThongSoKyThuat.addView(lnChiTiet);


        }
    }


    @Override
    public void HienThiSliderSanPham(String[] linkhinhsanpham) {

       fragmentList  = new ArrayList<>();


        for (int i=0;i<linkhinhsanpham.length;i++)
        {
            FragmentSliderChiTietSanPham fragmentSliderChiTietSanPham=new FragmentSliderChiTietSanPham();

            Bundle bundle = new Bundle();
            bundle.putString("linkhinh", TrangChuActivity.SERVER+linkhinhsanpham[i]);

            fragmentSliderChiTietSanPham.setArguments(bundle);

            fragmentList.add(fragmentSliderChiTietSanPham);
        }


        AdapterViewPagerSlider adapterViewPagerSlider=new AdapterViewPagerSlider(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapterViewPagerSlider);
        adapterViewPagerSlider.notifyDataSetChanged();


        ThemDotSlider(0);
        viewPager.addOnPageChangeListener(this);
    }

    public void HienThiDanhGia(List<DanhGia> danhGiaList) {

        AdapterDanhGia adapterDanhGia=new AdapterDanhGia(this,danhGiaList,3);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDanhGia);
        adapterDanhGia.notifyDataSetChanged();

    }

    @Override
    public void ThemGioHangThanhCong() {
       Toast.makeText(this,"Sản phẩm đã được thêm vào giỏ hàng",Toast.LENGTH_SHORT).show();
       txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this) ));
    }

    @Override
    public void ThemGioHangThatbai() {
        Toast.makeText(this,"Sản phẩm đã có trong giỏ hàng",Toast.LENGTH_SHORT).show();
    }

    private void ThemDotSlider(int vitrihientai)
    {
        txtDots=new TextView[fragmentList.size()];
        layoutdots.removeAllViews();
        for (int i=0;i<fragmentList.size();i++)
        {
            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226;"));
            txtDots[i].setTextSize(40);
            txtDots[i].setTextColor(getIdColor(R.color.colorSliderInActive));
            layoutdots.addView(txtDots[i]);
        }
        txtDots[vitrihientai].setTextColor(getIdColor(R.color.bgToolbar));

    }

    private Drawable getHinhChiTiet(int idDrawable)
    {
        Drawable drawable;
        if (Build.VERSION.SDK_INT>21)
        {
         drawable=  ContextCompat.getDrawable(this,idDrawable);
        }
        else
        {
         drawable=  getResources().getDrawable(idDrawable);
        }
        return drawable;
    }
    // lấy màu cho 3 dấu chấm
    private  int getIdColor(int idcolor)
    {
        int color=0;
        if (Build.VERSION.SDK_INT>21) //nếu phiên bản lớn hơn 21
        {//lấy color theo phiên bản 21
            color= ContextCompat.getColor(this,idcolor);
        }
        else
        {
            //phiên bản nhỏ hơn 21
            color=getResources().getColor(idcolor);
        }
        return color;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }
//sự kiện thay đổi
    @Override
    public void onPageSelected(int i) {
         ThemDotSlider(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View v) {
              int id=v.getId();
              switch (id)
              {
                  case R.id.txtVietDanhGia:
                      Intent iThemDanhGia=new Intent(this, ThemDanhGiaActivity.class);
                      iThemDanhGia.putExtra("masp",masp);
                      startActivity(iThemDanhGia);

                      break;
                  case R.id.txtXemTatCaNhanXet:
                      Intent iDanhSachDanhGia=new Intent(ChiTietSanPhamActivity.this, DanhSachDanhGiaActivity.class);
                      iDanhSachDanhGia.putExtra("masp",masp);
                      startActivity(iDanhSachDanhGia);

                      break;
                  case R.id.imgThemGioHang:
                      Fragment fragment=fragmentList.get(0);
                      View view=fragment.getView();
                      ImageView imageView=view.findViewById(R.id.imgHinhSlider);
                      //Lấy giá trị hình hiện tại

                      Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
                      //imgThemGioHang.setImageBitmap(bitmap);

                      ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
                      bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
                      byte[] hinhsanphamgiohang=outputStream.toByteArray();

                      sanPhamGioHang.setHinhgiohang(hinhsanphamgiohang);
                      sanPhamGioHang.setSOLUONG(1);

                      presenterLogicChiTietSanPham.ThemGioHang(sanPhamGioHang,this);
                      break;
                  case R.id.btnMuaNgay:
                      //add sản phẩm vào giỏ hàng

                      Fragment fragment1=fragmentList.get(0);
                      View view1=fragment1.getView();
                      ImageView imageView1=view1.findViewById(R.id.imgHinhSlider);
                      Bitmap bitmap1=((BitmapDrawable)imageView1.getDrawable()).getBitmap();


                      ByteArrayOutputStream outputStream1=new ByteArrayOutputStream();
                      bitmap1.compress(Bitmap.CompressFormat.PNG,100,outputStream1);
                      byte[] hinhsanphamgiohang1=outputStream1.toByteArray();

                      sanPhamGioHang.setHinhgiohang(hinhsanphamgiohang1);
                      sanPhamGioHang.setSOLUONG(1);

                      presenterLogicChiTietSanPham.ThemGioHang(sanPhamGioHang,this);
                      //chuyển thanh toán ngay
                      Intent iThanhToan= new Intent(ChiTietSanPhamActivity.this, ThanhToanActivity.class);
                      startActivity(iThanhToan);
                      break;





              }


    }
}
