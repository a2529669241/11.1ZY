package com.example.a111lianxi.Base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/*
 *功能:Fragment基类
 * 作者:姬裕源
 * 日期:2019-10-29
 */

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(ParveLayoutId(), container, false);
        initView(inflate);
        return inflate;
    }

    protected abstract void initView(View inflate);

    protected abstract int ParveLayoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intData();
    }

    protected abstract void intData();
}
