package com.rcq.xiaoshuo.view.mall;


import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.rcq.xiaoshuo.R;
import com.rcq.xiaoshuo.adapter.HotAdapter;
import com.rcq.xiaoshuo.adapter.TuijianAdapter;
import com.rcq.xiaoshuo.adapter.TuijianAdapter2;
import com.rcq.xiaoshuo.base.adapter.BaseSpaceItemDecoration;
import com.rcq.xiaoshuo.base.fragment.BaseFragment;
import com.rcq.xiaoshuo.base.fragment.BaseSwipeFragment;
import com.rcq.xiaoshuo.utils.UIUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MallListFragment extends BaseSwipeFragment {
    private Banner mBanner;
    private RecyclerView tuijian;
    private RecyclerView xixuan;
    private RecyclerView bangdan;
    private RecyclerView bangdan2;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mall_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindRefreshLayout(R.id.refresh_layout);
        mBanner = view.findViewById(R.id.banner);
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.banner1);
        list.add(R.mipmap.banner2);
        list.add(R.mipmap.banner1);
        mBanner.setImages(list).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setPadding(UIUtils.dip2px(getContext(),15),0,UIUtils.dip2px(getContext(),15),0);
                int res = (int) path;
                Glide.with(context).load(res).apply(RequestOptions.bitmapTransform(new RoundedCorners(20)).override(300,100)).into(imageView);
            }
        }).start();

        final ImageView imageView = view.findViewById(R.id.tu2);
        Glide.with(getContext()).load(R.mipmap.image2).apply(RequestOptions.bitmapTransform(new RoundedCorners(20)).override(300,100)).into(imageView);
        tuijian = view.findViewById(R.id.tuijian);
        xixuan = view.findViewById(R.id.xixuan);
        bangdan = view.findViewById(R.id.bangdan);
        bangdan2 = view.findViewById(R.id.bangdan2);
        initRecyclerView();
    }

    private void initRecyclerView() {
        tuijian.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        tuijian.addItemDecoration(new BaseSpaceItemDecoration(1,20));
        xixuan.setLayoutManager(new GridLayoutManager(getContext(),3));
        bangdan.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        bangdan.addItemDecoration(new BaseSpaceItemDecoration(1,20));
        bangdan2.setLayoutManager(new LinearLayoutManager(getContext()));
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list1.add("");
        }
        TuijianAdapter tuijianAdapter = new TuijianAdapter(getContext(),list1);
        TuijianAdapter2 tuijianAdapter2 = new TuijianAdapter2(getContext(),list1);
        tuijian.setAdapter(tuijianAdapter2);
        bangdan.setAdapter(tuijianAdapter2);
        xixuan.setAdapter(tuijianAdapter);
        HotAdapter  hotAdapter = new HotAdapter(getActivity(),list1);
        bangdan2.setAdapter(hotAdapter);
    }

    @Override
    public void onRefresh() {
        stopRefresh();
    }
}
