package com.bway.day_zk_0616.mvp.model;

import android.annotation.SuppressLint;
import android.util.Log;

import com.bway.day_zk_0616.mvp.model.bean.LoginBean;
import com.bway.day_zk_0616.utils.HttpConfig;
import com.bway.day_zk_0616.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class LoginModel {

    private static final String TAG = "LoginModel****";
    //获取网络请求
    public void doLogin(String mobile,String password,final OnCallBack onCallBack){

        String url= HttpConfig.URL_LOGIN;

        Map<String,String> map=new HashMap<>();
        map.put("mobile",mobile);
        map.put("password",password);

        OkHttpUtils.getInstance().doPost(url, map, new OkHttpUtils.onCallBack() {
            @Override
            public void onFaild(Exception e) {
                Log.d(TAG, "onFaild: "+e.getMessage());
            }


            @Override
            public void onResponse(String json) {

                //联网请求并进行解析
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(json, LoginBean.class);
                String msg = loginBean.getMsg();
                String code = loginBean.getCode();
                Log.d(TAG, "onResponse: "+json);

                if("0".equalsIgnoreCase(code)){

                    if(onCallBack!=null){
                        Log.d(TAG, "onResponse: ");
                        onCallBack.onSuccess(loginBean);
                    }
                }else{
                    if(onCallBack!=null){
                        onCallBack.onFaild(msg);
                    }
                }
            }
        });

    }

    //定义接口，进行接口回调
    public interface OnCallBack{

        void onSuccess(LoginBean loginBean);
        void onFaild(String error);
    }


}
