package com.rcq.xiaoshuo.view.mall;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.rcq.xiaoshuo.R;
import com.rcq.xiaoshuo.base.adapter.BaseFragmentStateAdapter;
import com.rcq.xiaoshuo.base.fragment.BaseFragment;
import com.rcq.xiaoshuo.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;


public class MallFragment extends BaseFragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<Fragment> mFragmentList;
    private String[] mTitles;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mall;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTabLayout = view.findViewById(R.id.tab_layout);
        mViewPager = view.findViewById(R.id.view_pager);
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTabLayout.getLayoutParams();
                params.topMargin = UIUtils.getStatuBarHeight(getContext());
                mTabLayout.setLayoutParams(params);
            }
        });

        init();
    }

    private void init() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new MallListFragment());
        mFragmentList.add(new MallListFragment());
        mFragmentList.add(new MallListFragment());
        mFragmentList.add(new MallListFragment());
        mFragmentList.add(new MallListFragment());
        mFragmentList.add(new MallListFragment());
        mTitles = getResources().getStringArray(R.array.mall_tabs);
        BaseFragmentStateAdapter adapter = new BaseFragmentStateAdapter(getChildFragmentManager(),mFragmentList,mTitles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(6);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}
