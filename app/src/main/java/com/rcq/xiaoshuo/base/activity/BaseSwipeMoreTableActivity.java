package com.rcq.xiaoshuo.base.activity;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;


/**
 * Created by Administrator on 2016/12/10 0010.
 * 服务于下拉刷新及上拉刷新需求
 *
 * https://github.com/Chanven/CommonPullToRefresh
 */
public abstract class BaseSwipeMoreTableActivity<T extends Object> extends BaseSwipeTableActivity<T> {

    public void bindSwipeMoreRecycler(int recyclerId, RecyclerView.Adapter adapter){
        bindSwipeMoreRecycler(recyclerId, new LinearLayoutManager(this), adapter);
    }

    public void bindSwipeMoreRecycler(int recyclerId, RecyclerView.LayoutManager lm, RecyclerView.Adapter adapter){
        super.bindSwipeRecycler(recyclerId, lm, adapter);

        if (this.refreshLayout !=null) {
            this.refreshLayout.setEnableLoadMore(true);
            this.refreshLayout.setRefreshFooter(new ClassicsFooter(this));
            this.refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    BaseSwipeMoreTableActivity.this.loadMore();
                }

                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    BaseSwipeMoreTableActivity.this.onRefresh();
                }
            });
        }else{
            Log.w("BaseSwipeMoreTable", "You must implement it first 'bindRefreshLayout()' mothed !");
        }
    }

    public abstract void loadMore();

    @Override
    public void startRefresh() {
        super.startRefresh();
    }

    @Override
    public void stopRefresh() {
       super.stopRefresh();
        if (this.refreshLayout != null){
            this.refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        setLoadMoreState(arrayList.size() > 0);
    }

    public void setLoadMoreState(boolean isLoadMore){
        if (this.refreshLayout != null) {
            this.refreshLayout.setEnableLoadMore(isLoadMore);
        }
    }
}
