package com.example.a111lianxi.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ParveLayoutId());
        initView();
        intData();
    }

    protected abstract void intData();

    protected abstract void initView();

    protected abstract int ParveLayoutId();
}
