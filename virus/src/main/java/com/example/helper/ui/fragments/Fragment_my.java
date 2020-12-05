package com.example.helper.ui.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helper.R;
import com.example.helper.base.BaseFragment;

public class Fragment_my extends BaseFragment {

    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_my,container,false);
    }

}
