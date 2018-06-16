package com.bway.day_zk_0616.mvp.presenter;

import com.bway.day_zk_0616.base.BasePresenter;
import com.bway.day_zk_0616.mvp.model.RegModel;
import com.bway.day_zk_0616.mvp.model.bean.LoginBean;
import com.bway.day_zk_0616.mvp.view.LoginView;

public class RegPresenter extends BasePresenter<LoginView> {

    private RegModel regModel1;

    public RegPresenter(LoginView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        regModel1 = new RegModel();

    }

    public void reg(String mobile,String password){

        regModel1.doReg(mobile, password, new RegModel.OnCallBack() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                if(view!=null){
                    view.onSuccess(loginBean);
                }
            }

            @Override
            public void onFaild(String error) {

                if(view!=null){
                    view.onFaild(error);
                }
            }
        });

    }
}
