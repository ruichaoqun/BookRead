package com.rcq.xiaoshuo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

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

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        TextView textView = holder.itemView.findViewById(R.id.text);
        if(position == 1){
            textView.setTextColor(0xFFE34F4F);
        }else{
            textView.setTextColor(0xFF333333);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
