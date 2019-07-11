package com.example.beestore.Model.ObjectClass;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LoadMoreScroll extends RecyclerView.OnScrollListener {
    int itemandautien=0;
    int tongitem=0;
    int itemloadtruoc=6;
    RecyclerView.LayoutManager layoutManager;
    ILoadMore loadMore;


    public LoadMoreScroll(RecyclerView.LayoutManager layoutManager,ILoadMore loadMore)
    {
        this.layoutManager=layoutManager;
        this.loadMore=loadMore;
    }




    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        tongitem = layoutManager.getItemCount();
        if (layoutManager instanceof LinearLayoutManager)//nếu được hiển thị
        {
            itemandautien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();   //lấy iteman trên đầu
        } else if (layoutManager instanceof GridLayoutManager) {
            itemandautien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }

        if (tongitem <= (itemandautien + itemloadtruoc))
        {
            loadMore.LoadMore(tongitem);
        }


    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
