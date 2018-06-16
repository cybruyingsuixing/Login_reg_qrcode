package com.bway.day_zk_0616.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity <P extends BasePresenter> extends AppCompatActivity{



    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(providId());

        initViews();
        initListener();
        //提供presenter
        presenter=proivdes();

        initData();
    }

    protected abstract void initData();

    protected abstract P proivdes();

    protected abstract void initListener();

    protected abstract void initViews();

    protected abstract int providId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }
}
