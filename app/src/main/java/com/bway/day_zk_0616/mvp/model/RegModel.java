package com.bway.day_zk_0616.mvp.model;

import com.bway.day_zk_0616.mvp.model.bean.LoginBean;
import com.bway.day_zk_0616.mvp.model.bean.RegBean;
import com.bway.day_zk_0616.utils.HttpConfig;
import com.bway.day_zk_0616.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class RegModel {

    //联网请求数据
    public void doReg(String mobile,String password,final OnCallBack onCallBack){

        String url= HttpConfig.URL_REG;
        Map<String,String> map=new HashMap<>();
        map.put("mobile",mobile);
        map.put("password",password);
        OkHttpUtils.getInstance().doPost(url, map, new OkHttpUtils.onCallBack() {
            @Override
            public void onFaild(Exception e) {

            }

            @Override
            public void onResponse(String json) {

                //即系数据
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(json, LoginBean.class);
                String msg = loginBean.getMsg();
                String code = loginBean.getCode();
                if("0".equalsIgnoreCase(code)){
                    if(onCallBack!=null){
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
