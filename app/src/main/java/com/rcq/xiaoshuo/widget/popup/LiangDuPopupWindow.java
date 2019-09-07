package com.rcq.xiaoshuo.widget.popup;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.PopupWindow;

import com.rcq.xiaoshuo.R;
import com.rcq.xiaoshuo.base.BasePopup;
import com.rcq.xiaoshuo.utils.UIUtils;

public class LiangDuPopupWindow extends BasePopup {
    private View layout;
    private View content;

    public LiangDuPopupWindow(Activity context) {
        super(context);
    }

    @Override
    public View setContextView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.popup_liangdu,null);
        layout = view.findViewById(R.id.layout);
        content = view.findViewById(R.id.content);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopup();
            }
        });
        return view;
    }

    @Override
    public void setPopupAttrs(PopupWindow popupWindow) {

    }

    @Override
    public void showPopup(int viewId) {
        super.showPopup(viewId);
        ObjectAnimator animator = ObjectAnimator.ofFloat(content,"translationY",content.getTranslationY(),0).setDuration(250);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    @Override
    public void dismissPopup() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(content,"translationY",content.getTranslationY(), UIUtils.dip2px(getContext(),227)).setDuration(250);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (popupWindow != null && popupWindow.isShowing())
                    popupWindow.dismiss();
            }
        });
        animator.start();
    }
}
