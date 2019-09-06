package com.rcq.xiaoshuo.view.classify;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rcq.xiaoshuo.adapter.ClassifyCotentAdapter;
import com.rcq.xiaoshuo.adapter.ClassifyLeftAdapter;
import com.rcq.xiaoshuo.base.adapter.BaseRecyclerAdapter;
import com.rcq.xiaoshuo.base.fragment.BaseSwipeTableFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rcq.xiaoshuo.R;
import com.rcq.xiaoshuo.adapter.BookShelfAdapter;
import com.rcq.xiaoshuo.base.adapter.BaseSpaceItemDecoration;
import com.rcq.xiaoshuo.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends BaseSwipeTableFragment<String> {

    private RecyclerView leftRV;
    protected List<String> leftLists = new ArrayList<>();

    Disposable mDisposable;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_classify;
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
        leftRV = view.findViewById(R.id.left_recycler_view);

        bindRefreshLayout(R.id.refresh_layout);
        bindSwipeRecycler(R.id.recycler_view, new GridLayoutManager(getContext(), 3),new ClassifyCotentAdapter(getContext(),arrayList));
        recyclerView.addItemDecoration(new BaseSpaceItemDecoration(1,15));

        leftLists.add("1");
        leftLists.add("1");
        leftLists.add("1");
        leftRV.setLayoutManager(new LinearLayoutManager(getContext()));
        ClassifyLeftAdapter leftAdapter = new ClassifyLeftAdapter(getContext(), leftLists);
        leftAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
                startRefresh();
            }
        });
        leftRV.setAdapter(leftAdapter);

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

}
