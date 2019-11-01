package com.example.a111lianxi;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {


    private List<LayerBean.ListdataBean> listdata;

    public MyAdapter(List<LayerBean.ListdataBean> listdata) {

        this.listdata = listdata;
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            convertView=View.inflate(parent.getContext(),R.layout.activity_buju,null);
            holder=new ViewHolder();
            holder.tu=convertView.findViewById(R.id.iv);
            holder.name=convertView.findViewById(R.id.tv_name);
            holder.content=convertView.findViewById(R.id.tv_text);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        LayerBean.ListdataBean listdataBean = listdata.get(position);
        holder.name.setText(listdataBean.getName());
        holder.content.setText(listdataBean.getContent());
        final ViewHolder finalholder=holder;
        NetUtil.getInstance().doGetPhoto(listdataBean.getAvatar(), new NetUtil.MyCallBack() {
            @Override
            public void onDoGetSuccess(String json) {

            }

            @Override
            public void onDoGetPhotoSuccess(Bitmap bitmap) {
                finalholder.tu.setImageBitmap(bitmap);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        ImageView tu;
        TextView name;
        TextView content;
    }
}
