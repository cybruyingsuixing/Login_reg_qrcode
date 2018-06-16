package com.bway.day_zk_0616.mvp.view;

import com.bway.day_zk_0616.base.IView;
import com.bway.day_zk_0616.mvp.model.bean.LoginBean;

public interface LoginView extends IView {


    void onSuccess(LoginBean loginBean);
    void onFaild(String error);

}
