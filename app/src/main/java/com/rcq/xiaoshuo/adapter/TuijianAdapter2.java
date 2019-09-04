package com.rcq.xiaoshuo.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rcq.xiaoshuo.R;
import com.rcq.xiaoshuo.base.adapter.BaseRecyclerAdapter;

import java.util.List;

public class TuijianAdapter2 extends BaseRecyclerAdapter<TuijianAdapter2.ViewHolder,String> {


    public TuijianAdapter2(Context context, List<String> arrayList) {
        super(context, arrayList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_adapter_tuijian2;
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
