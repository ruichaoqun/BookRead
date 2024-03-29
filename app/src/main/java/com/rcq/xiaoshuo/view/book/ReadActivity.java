package com.rcq.xiaoshuo.view.book;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.Gravity;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.rcq.xiaoshuo.R;
import com.rcq.xiaoshuo.adapter.ZhangjieAdapter;
import com.rcq.xiaoshuo.base.adapter.BaseRecyclerAdapter;
import com.rcq.xiaoshuo.utils.UIUtils;
import com.rcq.xiaoshuo.widget.popup.LiangDuPopupWindow;
import com.rcq.xiaoshuo.widget.popup.LookVideoPopupWindow;
import com.rcq.xiaoshuo.widget.popup.ReadSettingPopupWindow;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class ReadActivity extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout mDrawerLayout;
    RecyclerView mRecyclerView;

    RelativeLayout titleBar;
    RelativeLayout bottomLayout;
    boolean isSettingHide = true;
    ZhangjieAdapter mAdapter;
    ReadSettingPopupWindow mSettingPopupWindow;
    LiangDuPopupWindow mLiangDuPopupWindow;
    LookVideoPopupWindow mLookVideoPopupWindow;

    View rootView;
    TextView tvRead,tvTitle,tvPage,tvYeJian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_read);
         mDrawerLayout = findViewById(R.id.drawer_layout);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        mAdapter = new ZhangjieAdapter(this,list);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        titleBar = findViewById(R.id.title_bar);
        bottomLayout = findViewById(R.id.bottom_layout);
        findViewById(R.id.text).setOnClickListener(this);
        findViewById(R.id.mulu).setOnClickListener(this);
        findViewById(R.id.yejian).setOnClickListener(this);
        findViewById(R.id.liangdu).setOnClickListener(this);
        findViewById(R.id.shezhi).setOnClickListener(this);
        findViewById(R.id.next_tv).setOnClickListener(this);
        tvRead = findViewById(R.id.text);
        rootView = findViewById(R.id.root_view);
        tvTitle = findViewById(R.id.title_tv);
        tvPage = findViewById(R.id.page_tv);
        tvYeJian = findViewById(R.id.yejian);
    }

    private boolean isNight = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text:
                isSettingHide = !isSettingHide;
                starAnimation();
                break;
            case R.id.mulu:
                isSettingHide = true;
                starAnimation();
                mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.yejian:
                isSettingHide = true;
                starAnimation();

                if (!isNight){
                    isNight = true;
                    rootView.setBackgroundColor(Color.parseColor("#001525"));
                    tvRead.setTextColor(Color.parseColor("#2C4E5A"));
                    tvTitle.setTextColor(Color.parseColor("#2C4E5A"));
                    tvPage.setTextColor(Color.parseColor("#2C4E5A"));

                    tvYeJian.setText("日间");
                    tvYeJian.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.rjian, 0, 0);
                } else {
                    isNight = false;
                    rootView.setBackgroundColor(Color.parseColor("#D4BD9D"));
                    tvRead.setTextColor(Color.parseColor("#333333"));
                    tvTitle.setTextColor(Color.parseColor("#FFB29367"));
                    tvPage.setTextColor(Color.parseColor("#FFB29367"));

                    tvYeJian.setText("夜间");
                    tvYeJian.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.yejian, 0, 0);
                }
                break;
            case R.id.liangdu:
                isSettingHide = true;
                starAnimation();
                showLiangduPop();
                break;
            case R.id.shezhi:
                isSettingHide = true;
                starAnimation();
                showSettingPop();
                break;
            case R.id.next_tv:
                isSettingHide = true;
                starAnimation();
                showLookVideo();
                break;
        }
    }

    private void showLiangduPop() {
        if(mLiangDuPopupWindow == null){
            mLiangDuPopupWindow = new LiangDuPopupWindow(this);
        }
        Observable.timer(300, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mLiangDuPopupWindow.showPopup(R.id.drawer_layout);
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void showSettingPop() {
        if(mSettingPopupWindow == null){
            mSettingPopupWindow = new ReadSettingPopupWindow(this);
        }
        Observable.timer(300, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mSettingPopupWindow.showPopup(R.id.drawer_layout);
                    }
                });
    }

    private void showLookVideo(){
        if (mLookVideoPopupWindow == null)
            mLookVideoPopupWindow = new LookVideoPopupWindow(this);

        Observable.timer(300, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mLookVideoPopupWindow.showPopup(R.id.drawer_layout);
                    }
                });
    }

    private void starAnimation() {
        if(isSettingHide){
            ObjectAnimator.ofFloat(titleBar,"translationY",titleBar.getTranslationY(), -UIUtils.dip2px(this,45))
                    .setDuration(250).start();
            ObjectAnimator.ofFloat(bottomLayout,"translationY",bottomLayout.getTranslationY(), UIUtils.dip2px(this,120))
                    .setDuration(250).start();
        }else{
            ObjectAnimator.ofFloat(titleBar,"translationY", titleBar.getTranslationY(),0)
                    .setDuration(250).start();
            ObjectAnimator.ofFloat(bottomLayout,"translationY", bottomLayout.getTranslationY(),0)
                    .setDuration(250).start();
        }

    }
}
