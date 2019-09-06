package com.rcq.xiaoshuo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rcq.xiaoshuo.R;
import com.rcq.xiaoshuo.base.adapter.BaseRecyclerAdapter;

import java.util.List;

public class ClassifyLeftAdapter extends BaseRecyclerAdapter<ClassifyLeftAdapter.ViewHolder,String> {


    public ClassifyLeftAdapter(Context context, List<String> arrayList) {
        super(context, arrayList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_adapter_classify_left_view;
    }

    @Override
    public ViewHolder createNewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        TextView textView = holder.itemView.findViewById(R.id.text3);
        if(position%2 == 0){
            textView.setText("男生");
            textView.setTextColor(Color.parseColor("#56cdba"));
        }else{
            textView.setText("女生");
            textView.setTextColor(Color.parseColor("#000000"));
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
