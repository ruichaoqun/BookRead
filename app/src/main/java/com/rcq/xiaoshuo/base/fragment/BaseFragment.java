package com.rcq.xiaoshuo.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


/**
 * Created by Administrator on 2016/10/11 0011.
 */
public abstract class BaseFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(getLayoutId(), container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public abstract int getLayoutId();

    public String getViewStr(TextView textView){
        return textView.getText().toString().trim();
    }

    public int getResColor(int colorId){
        return ContextCompat.getColor(getContext(), colorId);
    }
}
