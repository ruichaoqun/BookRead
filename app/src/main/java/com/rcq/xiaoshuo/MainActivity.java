package com.rcq.xiaoshuo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.rcq.xiaoshuo.base.adapter.BaseFragmentStateAdapter;
import com.rcq.xiaoshuo.utils.UIUtils;
import com.rcq.xiaoshuo.view.bookshelf.BookShelfFragment;
import com.rcq.xiaoshuo.view.classify.ClassifyFragment;
import com.rcq.xiaoshuo.view.mall.MallFragment;
import com.rcq.xiaoshuo.view.person.PersonFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fragment> mFragmentList;
    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            //让contentView延伸到状态栏并且设置状态栏颜色透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_main);

        mBottomNavigationView = findViewById(R.id.nav_view);
        mBottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mViewPager = findViewById(R.id.view_pager);
        initPager();

    }

    private void initPager() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new MallFragment());
        mFragmentList.add(new BookShelfFragment());
        mFragmentList.add(new ClassifyFragment());
        mFragmentList.add(new PersonFragment());
        BaseFragmentStateAdapter adapter = new BaseFragmentStateAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mBottomNavigationView.setSelectedItemId(R.id.navigation_mall);
                        break;
                    case 1:
                        mBottomNavigationView.setSelectedItemId(R.id.navigation_bookshelf);
                        break;
                    case 2:
                        mBottomNavigationView.setSelectedItemId(R.id.navigation_classify);
                        break;
                    case 3:
                        mBottomNavigationView.setSelectedItemId(R.id.navigation_person);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_mall:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_bookshelf:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_classify:
                    mViewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_person:
                    mViewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

}
