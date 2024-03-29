package com.rcq.xiaoshuo.base.fragment;

import android.view.View;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rcq.xiaoshuo.base.adapter.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/13 0013.
 * SwipeTable基类去除上拉加载功能，作为普通的列表展示框体使用
 */
public abstract class BaseSwipeTableFragment<T extends Object> extends BaseSwipeFragment implements BaseRecyclerAdapter.OnItemClickListener, BaseRecyclerAdapter.OnItemLongClickListener {

    protected RecyclerView recyclerView;
    protected List<T> arrayList = new ArrayList<>();
    private RecyclerView.Adapter adapter;

    public RecyclerView bindSwipeRecycler(int recyclerId, RecyclerView.Adapter adapter){
        return bindSwipeRecycler(recyclerId, new LinearLayoutManager(getContext()), adapter);
    }

    public RecyclerView bindSwipeRecycler(int recyclerId, RecyclerView.LayoutManager lm, RecyclerView.Adapter adapter){
        recyclerView = (RecyclerView) getView().findViewById(recyclerId);

        this.adapter = adapter;
        if (adapter instanceof BaseRecyclerAdapter) {
            ((BaseRecyclerAdapter) adapter).setOnItemClickListener(this);
            ((BaseRecyclerAdapter) adapter).setOnItemLongClickListener(this);
        }

        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {

    }

    @Override
    public void onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, int position) {

    }

    /** 刷新适配器 */
    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }
}
