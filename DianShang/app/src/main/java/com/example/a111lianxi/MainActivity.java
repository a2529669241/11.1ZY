package com.example.a111lianxi;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.a111lianxi.Base.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.annotations.Until;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private GridView gridView;
    private int pager = 1;
    private boolean isloadMore;
    private ArrayList<LayerBean.ListdataBean> list = new ArrayList<>();

    @Override
    protected void intData() {
        String url = "";
        if (pager == 1) {
            url = "http://blog.zhaoliang5156.cn/api/news/lawyer.json";
        } else if (pager == 2) {
            url = "http://blog.zhaoliang5156.cn/api/news/lawyer1.json";
        } else if (pager == 3) {
            url = "http://blog.zhaoliang5156.cn/api/news/lawyer2.json";
        }
        NetUtil.getInstance().doGet(url, new NetUtil.MyCallBack() {
            @Override
            public void onDoGetSuccess(String json) {
                LayerBean layerBean = new Gson().fromJson(json, LayerBean.class);
                List<LayerBean.ListdataBean> listdata = layerBean.getListdata();
                MyAdapter myAdapter = new MyAdapter(listdata);
                gridView.setAdapter(myAdapter);
            }

            @Override
            public void onDoGetPhotoSuccess(Bitmap bitmap) {

            }
        });
    }

    @Override
    protected void initView() {
        gridView = findViewById(R.id.gv);
    }

    @Override
    protected int ParveLayoutId() {
        return R.layout.activity_main;
    }
}
