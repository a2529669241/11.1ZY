package com.example.a111lianxi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 *@auther:姬裕源
 *@Date: 2019/11/1
 *@Time:19:00
 *@Description:${DESCRIPTION}
 **/
public class NetUtil {
    private static final NetUtil ourInstance = new NetUtil();

    public static NetUtil getInstance() {
        return ourInstance;
    }

    private NetUtil() {
    }

    public void doGet(final String httpUrl, final MyCallBack myCallBack){
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                myCallBack.onDoGetSuccess(s);
            }

            @Override
            protected String doInBackground(String... strings) {
                String json="";
                InputStream inputStream=null;
                try {
                    URL url = new URL(httpUrl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.connect();

                    if (httpURLConnection.getResponseCode()==200){
                        inputStream = httpURLConnection.getInputStream();
                        json = io2String(inputStream);
                    }else{
                        Log.e("tag","请求失败" );
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return json;
            }
        }.execute();
    }

    public void doGetPhoto(final String httpUrl, final MyCallBack myCallBack){
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                myCallBack.onDoGetPhotoSuccess(bitmap);
            }

            @Override
            protected Bitmap doInBackground(String... strings) {
                InputStream inputStream=null;
                Bitmap bitmap=null;
                try {
                    URL url = new URL(httpUrl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.connect();

                    if (httpURLConnection.getResponseCode()==200){
                        inputStream = httpURLConnection.getInputStream();
                        bitmap = io2Bitmap(inputStream);
                    }else{
                        Log.e("tag","请求失败" );
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return bitmap;
            }
        }.execute();
    }

    public String io2String(InputStream inputStream){
        byte[] bytes = new byte[1024];
        int len=-1;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String json="";
        try {
            while ((len=inputStream.read(bytes))!=-1){
                byteArrayOutputStream.write(bytes,0 ,len );
            }
            byte[] bytes1 = byteArrayOutputStream.toByteArray();
            json = new String(bytes1);
        }catch (IOException e){
            e.printStackTrace();
        }
        return json;
    }

    public Bitmap io2Bitmap(InputStream inputStream){
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }

    public interface MyCallBack{
        void onDoGetSuccess(String json);
        void onDoGetPhotoSuccess(Bitmap bitmap);
    }

    public boolean hasNet(Context context){
        ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
        if(activeNetworkInfo!=null&&activeNetworkInfo.isAvailable()){
            return true;
        }else{
            return false;
        }
    }

    public boolean isWIFI(Context context){
        ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
        if(activeNetworkInfo!=null&&activeNetworkInfo.getType()==ConnectivityManager.TYPE_WIFI){
            return true;
        }else{
            return false;
        }
    }

    public boolean isMOBILE(Context context){
        ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
        if(activeNetworkInfo!=null&&activeNetworkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
            return true;
        }else{
            return false;
        }
    }
}
