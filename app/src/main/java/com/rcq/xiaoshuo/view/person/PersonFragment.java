package com.rcq.xiaoshuo.view.person;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcq.xiaoshuo.R;
import com.rcq.xiaoshuo.base.fragment.BaseFragment;


public class PersonFragment extends BaseFragment {


    @Override
    public int getLayoutId() {
        return R.layout.fragment_person;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.feed_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),FeedbackActivity.class));
            }
        });
    }
}
