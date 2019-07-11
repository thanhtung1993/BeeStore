package com.example.beestore.View.TrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.beestore.Adapter.ExpandAdapter;
import com.example.beestore.Adapter.ViewPagerAdapter;
import com.example.beestore.Model.DangNhap.ModelDangNhap;
import com.example.beestore.Model.ObjectClass.LoaiSanPham;
import com.example.beestore.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.beestore.Presenter.TrangChu.XuLyMenu.PresenterLogicXuLyMenu;
import com.example.beestore.R;
import com.example.beestore.View.DangNhap.DangNhapActivity;
import com.example.beestore.View.GioHang.GioHangActivity;
import com.example.beestore.View.TimKiem.TimKiemActivity;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu, AppBarLayout.OnOffsetChangedListener , View.OnClickListener {


    public static final String SERVER_NAME= "http://10.252.10.104:8080/weblazada/loaisanpham.php";
    public static final String SERVER= "http://10.252.10.104:8080/weblazada";


    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    PresenterLogicXuLyMenu logicXuLyMenu;
    String tennguoidung="";
    MenuItem itemDangNhap,menuITDangXuat;
    AccessToken accessToken;
    Menu menu;
    TextView txtGioHang;
    Button btnSearch;
    boolean onPause=false;
    GoogleSignInResult googleSignInResult; GoogleApiClient mGoogleApiClient;


    ModelDangNhap modelDangNhap;


    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;

   ExpandableListView expandableListView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.trangchu_layout);

        toolbar=findViewById(R.id.toolbar);
        tabLayout=findViewById(R.id.tabs);
        viewPager=findViewById(R.id.viewpager);
        drawerLayout=findViewById(R.id.drawer);
        expandableListView=findViewById(R.id.epMenu);
        btnSearch=findViewById(R.id.btnSearch);

        collapsingToolbarLayout=findViewById(R.id.collapToolbar);
        appBarLayout=findViewById(R.id.appbar);



        toolbar.setTitle("");


        setSupportActionBar(toolbar);// gán toolbar cho action bar


        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);//gán nút menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerToggle.syncState();//đồng bộ dữ liệu drawerlayout

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



        logicXuLyMenu=new PresenterLogicXuLyMenu(this);
        logicXuLyMenu.LayDanhSachMenu();
        modelDangNhap=new ModelDangNhap();

        appBarLayout.addOnOffsetChangedListener(this);
        btnSearch.setOnClickListener(this);

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (onPause)
        {
            PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
            txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));

        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause=true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//khởi tạo menu
        getMenuInflater().inflate(R.menu.menutrangchu,menu);
        this.menu=menu;

        //tạo số lượng giỏ hàng
        MenuItem itGioHang=this.menu.findItem(R.id.itGioHang);
        View giaoDienCustomGioHang= MenuItemCompat.getActionView(itGioHang);
        txtGioHang=giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);

        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent iGioHang=new Intent(TrangChuActivity.this, GioHangActivity.class);
               startActivity(iGioHang);
            }
        });





        itemDangNhap = menu.findItem(R.id.itDangNhap);
        menuITDangXuat = menu.findItem(R.id.itDangXuat);

        accessToken=   logicXuLyMenu.LayTenNguoiDungFB();
      //  googleSignInResult = modelDangNhap.LayThongDangNhapGoogle(mGoogleApiClient);


        if (accessToken !=null)
        {
            GraphRequest graphRequest=GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {




                    try {
                        tennguoidung=object.getString("name");
                        Log.d("token", tennguoidung);
                       itemDangNhap.setTitle(tennguoidung);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


            Bundle parameter=new Bundle();
            parameter.putString("fields","name");

            graphRequest.setParameters(parameter);
            graphRequest.executeAsync();



        }
        if(googleSignInResult != null){
            itemDangNhap.setTitle(googleSignInResult.getSignInAccount().getDisplayName());
            Log.d("goo",googleSignInResult.getSignInAccount().getDisplayName());
        }

        String tennv = modelDangNhap.LayCachedDangNhap(this);
        if(!tennv.equals("")){
            Log.d("dn",tennv);

           itemDangNhap.setTitle(tennv);

        }


        if (accessToken !=null || !tennv.equals(""))
        {

            menuITDangXuat.setVisible(true);
        }

        return true;
   }

                        @Override
    public boolean onOptionsItemSelected(MenuItem item) {// tạo sự kiện menu
       if (drawerToggle.onOptionsItemSelected(item))
      {
           return true;//tạo sự kiện click
       }
       int id = item.getItemId();
       switch (id)
       {
           case R.id.itDangNhap:
               if (accessToken==null &&modelDangNhap.LayCachedDangNhap(this).equals("")) {
                   Intent idangnhap=new Intent(this, DangNhapActivity.class);
                   startActivity(idangnhap);
               }
               break;
           case R.id.itDangXuat:
               if (accessToken!=null)
               {
                   LoginManager.getInstance().logOut();
                   this.menu.clear();
                   this.onCreateOptionsMenu(this.menu);
               }

               if(googleSignInResult != null){
                   Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                   this.menu.clear();
                   this.onCreateOptionsMenu(this.menu);

               }
               if (!modelDangNhap.LayCachedDangNhap(this).equals(""))
               {
                   modelDangNhap.CapNhatCachedDangNhap(this,"");
                   this.menu.clear();
                   this.onCreateOptionsMenu(this.menu);
               }
               break;
           case R.id.itSearch:
               Intent itimkiem=new Intent(TrangChuActivity.this, TimKiemActivity.class);
               startActivity(itimkiem);
               break;




       }
       return true;



     }




    @Override
    public void HienThiDanhSachMenu(List<LoaiSanPham> loaiSanPhamList) {
      //  Log.d("kiemtra",loaiSanPhamList.get(0).getTENLOAISP());
        ExpandAdapter expandAdapter = new ExpandAdapter(this,loaiSanPhamList);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();


    }

    @Override
    //lắng nghe sự kiện appbar
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

       // Log.d("kiemtra", String.valueOf(collapsingToolbarLayout.getHeight()+i+ ViewCompat.getMinimumHeight(collapsingToolbarLayout)));
        if (collapsingToolbarLayout.getHeight() + i <=1.5* ViewCompat.getMinimumHeight(collapsingToolbarLayout)){
            LinearLayout linearLayout=appBarLayout.findViewById(R.id.lnsearch);
            linearLayout.setAlpha(0);

            MenuItem itSearch=menu.findItem(R.id.itSearch);
            itSearch.setVisible(true);
        }
        else
        {
            LinearLayout linearLayout=appBarLayout.findViewById(R.id.lnsearch);
            linearLayout.setAlpha(1);
try {
    MenuItem itSearch=menu.findItem(R.id.itSearch);
    itSearch.setVisible(false);
}
catch (Exception e)
{

}


        }
    }


    @Override
    public void onClick(View v) {
         int id=v.getId();
         switch (id)
         {
             case R.id.btnSearch:
                 Intent itimkiem=new Intent(TrangChuActivity.this,TimKiemActivity.class);
                 startActivity(itimkiem);
                 break;

         }
    }
}