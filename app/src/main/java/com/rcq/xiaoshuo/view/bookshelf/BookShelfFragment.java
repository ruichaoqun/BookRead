package com.rcq.xiaoshuo.view.bookshelf;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rcq.xiaoshuo.R;
import com.rcq.xiaoshuo.adapter.BookShelfAdapter;
import com.rcq.xiaoshuo.base.adapter.BaseSpaceItemDecoration;
import com.rcq.xiaoshuo.base.fragment.BaseSwipeTableFragment;
import com.rcq.xiaoshuo.utils.UIUtils;
import com.rcq.xiaoshuo.view.book.BookDetailctivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookShelfFragment extends BaseSwipeTableFragment<String> {
    Disposable mDisposable;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_book_shelf;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final View view1 = view.findViewById(R.id.text);
        view1.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view1.getLayoutParams();
                params.topMargin = UIUtils.getStatuBarHeight(getContext());
                view1.setLayoutParams(params);
            }
        });
        bindRefreshLayout(R.id.refresh_layout);
        bindSwipeRecycler(R.id.recycler_view,new BookShelfAdapter(getContext(),arrayList));
        recyclerView.addItemDecoration(new BaseSpaceItemDecoration(1,15));
        startRefresh();
    }

    @Override
    public void onRefresh() {
        mDisposable = Observable.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        for (int i = 0; i < 10; i++) {
                            arrayList.add("");
                        }
                        stopRefresh();
                        notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
        super.onItemClick(viewHolder, view, position);
        startActivity(new Intent(getContext(), BookDetailctivity.class));
    }
}
