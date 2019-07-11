package com.example.beestore.View.TimKiem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.beestore.Adapter.AdapterTopDienThoaiDienTu;
import com.example.beestore.Model.ObjectClass.ILoadMore;
import com.example.beestore.Model.ObjectClass.LoadMoreScroll;
import com.example.beestore.Model.ObjectClass.SanPham;
import com.example.beestore.Presenter.TimKiem.PresenterLogicTimKiem;
import com.example.beestore.R;

import java.util.List;


public class TimKiemActivity  extends AppCompatActivity implements ViewTimKiem, ILoadMore, SearchView.OnQueryTextListener {
    Toolbar toolbar;
    RecyclerView recyclerView;
    PresenterLogicTimKiem presenterLogicTimKiem;
    SearchView searchView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timkiem);

        toolbar=findViewById(R.id.toolbar);
        recyclerView=findViewById(R.id.recyclerTimKiem);

        setSupportActionBar(toolbar);
        presenterLogicTimKiem=new PresenterLogicTimKiem(this);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timkiem,menu);

        MenuItem itSearch=menu.findItem(R.id.itSearch);
        searchView= (SearchView) itSearch.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public void TimKiemThanhCong(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu=new AdapterTopDienThoaiDienTu(this,R.layout.custom_layout_topdienthoaivamaytinhbang,sanPhamList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopDienThoaiDienTu);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager,this));
        adapterTopDienThoaiDienTu.notifyDataSetChanged();

    }

    @Override
    public void TimKiemThatBai() {
        Toast.makeText(this,"Không tìm thấy sản phẩm mà bạn yêu cầu",Toast.LENGTH_SHORT).show();
; }

    @Override
    public void LoadMore(int tongitem) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenterLogicTimKiem.TimKiemSanPhamTheoTenSanPham(query,0);
        return false;
    }
   //gõ tới đâu sớt tới đó
    @Override
    public boolean onQueryTextChange(String newText) {

        return false;
    }
}
