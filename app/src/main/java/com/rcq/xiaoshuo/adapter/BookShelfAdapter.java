package com.rcq.xiaoshuo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rcq.xiaoshuo.R;
import com.rcq.xiaoshuo.base.adapter.BaseRecyclerAdapter;

import java.util.List;

public class BookShelfAdapter extends BaseRecyclerAdapter<BookShelfAdapter.ViewHolder,String> {


    public BookShelfAdapter(Context context, List<String> arrayList) {
        super(context, arrayList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_adapter_book_shelf;
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
            textView.setText("连载中");
            textView.setTextColor(getColor(R.color.colorPrimary));
        }else{
            textView.setText("完结");
            textView.setTextColor(0xff999999);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
