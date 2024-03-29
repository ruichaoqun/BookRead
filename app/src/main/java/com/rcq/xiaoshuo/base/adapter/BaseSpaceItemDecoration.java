package com.rcq.xiaoshuo.base.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rcq.xiaoshuo.App;
import com.rcq.xiaoshuo.utils.UIUtils;


/**
 * Created by Administrator on 2016/11/18 0018.
 * 用户ReceyclerView适配器Item之间的间距设置
 */
public class BaseSpaceItemDecoration extends RecyclerView.ItemDecoration {

//    private Logger logger = new Logger(this.getClass().getSimpleName());
    private int spanCount;//列数
    private int space;//行距
    private int cutSpace;//半个单位行距
    public static final int ALL_SPACE_SET = -1;//全域设置边距
    private boolean lockLength;//锁定宽高（只对大于2的多列数据支持）
    private boolean needTop;//需要显示首行顶部间距

    public BaseSpaceItemDecoration(int spanCount,int space) {
        this(spanCount, space, false);
    }

    public BaseSpaceItemDecoration(int spanCount,int space, boolean lockLength){
        this.lockLength = lockLength;
        this.spanCount = spanCount;
        this.space = UIUtils.dip2px(App.getContext(), space);
        this.cutSpace = this.space / 3;
    }

    public BaseSpaceItemDecoration(int spanCount,int space , boolean lockLength,boolean needTop) {
        this.lockLength = lockLength;
        this.needTop = needTop;
        this.spanCount = spanCount;
        this.space = UIUtils.dip2px(App.getContext(), space);
        this.cutSpace = this.space / 3;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = 0;
        outRect.top = 0;
        outRect.right = 0;
        outRect.bottom = 0;

        int num = parent.getChildAdapterPosition(view);

        if (num ==0&&needTop){
            outRect.top = space;
        }

        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if(manager instanceof LinearLayoutManager){//单列模式
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
            if(linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL)
                outRect.right = space;
            else
                outRect.bottom = space;
        }

        if(manager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            GridLayoutManager.SpanSizeLookup lookup = gridLayoutManager.getSpanSizeLookup();
            if (num >= 0) {
                if (lookup.getSpanSize(num) == spanCount) {//完全合并列
                    outRect.bottom = space;
                } else {//非合并列
                    //lookup.getSpanIndex(num,2) 直接判断该item在列的第几列
                    //lookup.getSpanSize(num) 判断该item是否是合并列，大于1就是合并列
                    if(lookup.getSpanIndex(num,spanCount) == 0){//左侧
                        outRect.left = 0;
                        outRect.right = cutSpace;
                        outRect.bottom = space;
                    }else if(lookup.getSpanIndex(num,spanCount) == spanCount - 1){//右侧
                        outRect.left = cutSpace;
                        outRect.right = 0;
                        outRect.bottom = space;
                    }else{//中间
                        outRect.left = cutSpace;
                        outRect.right = cutSpace;
                        outRect.bottom = space;
                    }
                }
            }
        }
    }
}
