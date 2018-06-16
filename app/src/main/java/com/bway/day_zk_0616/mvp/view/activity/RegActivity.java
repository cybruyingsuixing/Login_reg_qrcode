package com.bway.day_zk_0616.mvp.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bway.day_zk_0616.R;
import com.bway.day_zk_0616.base.BaseActivity;
import com.bway.day_zk_0616.mvp.model.bean.LoginBean;
import com.bway.day_zk_0616.mvp.presenter.LoginPresenter;
import com.bway.day_zk_0616.mvp.presenter.RegPresenter;
import com.bway.day_zk_0616.mvp.view.LoginView;

public class RegActivity extends BaseActivity<RegPresenter>  implements View.OnClickListener,LoginView{


    private EditText mobile;
    private EditText password;
    private Button btn_reg;

    @Override
    protected void initData() {

    }

    @Override
    protected RegPresenter proivdes() {
        return new RegPresenter(this);
    }

    @Override
    protected void initListener() {
     btn_reg.setOnClickListener(this);
    }

    @Override
    protected void initViews() {

        //获取id
        mobile = findViewById(R.id.reg_mobile);
        password = findViewById(R.id.reg_password);
        btn_reg = findViewById(R.id.reg_but);

    }

    @Override
    protected int providId() {
        return R.layout.activity_reg;
    }

    @Override
    public void onClick(View v) {

        //获取输入框内容
        String m = mobile.getText().toString();
        String p = password.getText().toString();
        presenter.reg(m,p);
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                Intent it = new Intent(RegActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    public void onFaild(String error) {
       Toast.makeText(RegActivity.this,"注册失败"+error,Toast.LENGTH_SHORT).show();
    }
}
