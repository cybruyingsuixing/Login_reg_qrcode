package com.bway.day_zk_0616.mvp.presenter;

import com.bway.day_zk_0616.base.BasePresenter;
import com.bway.day_zk_0616.mvp.model.LoginModel;
import com.bway.day_zk_0616.mvp.model.bean.LoginBean;
import com.bway.day_zk_0616.mvp.view.LoginView;

public class LoginPresenter extends BasePresenter<LoginView> {


    private LoginModel loginModel;

    public LoginPresenter(LoginView view) {
        super(view);
    }

    @Override
    protected void initModel() {

        loginModel = new LoginModel();
    }


    public void login(String mobile,String password){

        loginModel.doLogin(mobile, password, new LoginModel.OnCallBack() {
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
