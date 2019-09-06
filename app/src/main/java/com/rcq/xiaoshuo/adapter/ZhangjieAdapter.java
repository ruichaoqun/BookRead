package com.rcq.xiaoshuo.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rcq.xiaoshuo.R;
import com.rcq.xiaoshuo.base.adapter.BaseRecyclerAdapter;

import java.util.List;

public class ZhangjieAdapter extends BaseRecyclerAdapter<ZhangjieAdapter.ViewHolder,String> {


    public ZhangjieAdapter(Context context, List<String> arrayList) {
        super(context, arrayList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_dapter_zhangjie;
    }

    @Override
    public ViewHolder createNewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
