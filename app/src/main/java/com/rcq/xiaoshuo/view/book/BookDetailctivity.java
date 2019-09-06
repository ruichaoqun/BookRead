package com.rcq.xiaoshuo.view.book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rcq.xiaoshuo.R;
import com.rcq.xiaoshuo.adapter.TuijianAdapter;
import com.rcq.xiaoshuo.base.activity.BaseSwipeTableActivity;

public class BookDetailctivity extends BaseSwipeTableActivity<String> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detailctivity);
        bindRefreshLayout(R.id.refresh_layout);
        for (int i = 0; i < 6; i++) {
            arrayList.add("");
        }
        bindSwipeRecycler(R.id.recycler_view,new GridLayoutManager(this,3),new TuijianAdapter(this,arrayList));
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookDetailctivity.this,ReadActivity.class));
            }
        });
    }

    @Override
    public void onRefresh() {
        arrayList.clear();
        for (int i = 0; i < 6; i++) {
            arrayList.add("");
        }
        notifyDataSetChanged();
        stopRefresh();
    }
}
