package com.bway.day_zk_0616.mvp.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bway.day_zk_0616.R;
import com.bway.day_zk_0616.base.BaseActivity;

import com.bway.day_zk_0616.mvp.model.bean.LoginBean;
import com.bway.day_zk_0616.mvp.presenter.LoginPresenter;
import com.bway.day_zk_0616.mvp.view.LoginView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import java.util.Map;

public class LoginActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener,LoginView {

    private EditText ed_mobile,password;
    private Button btn_login,btn_reg;
    private EditText qrStrEditText;
    private ImageView qrImgImageView;
    private CheckBox mCheckBox;
    private Button scanBarCodeButton;
    private Button generateQRCodeButton;

    @Override
    protected void initData() {

    }

    @Override
    protected LoginPresenter proivdes() {
        return new LoginPresenter((LoginView) this);
    }

    @Override
    protected void initListener() {

     btn_login.setOnClickListener(this);
       btn_login.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                UMShareAPI umShareAPI = UMShareAPI.get(LoginActivity.this);
                umShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
                return false;
            }
        });


        generateQRCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contentString = qrStrEditText.getText().toString();

                // 根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
                Bitmap qrCodeBitmap = EncodingUtils.createQRCode("cccccccccccc", 350, 350, mCheckBox.isChecked() ? BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher) : null);
                qrImgImageView.setImageBitmap(qrCodeBitmap);

            }
        });

    }

    @Override
    protected void initViews() {

        //获取id
        ed_mobile = findViewById(R.id.main_mobile);
        password=findViewById(R.id.main_password);
        btn_login = findViewById(R.id.main_login);
        btn_reg=findViewById(R.id.main_reg);
        //resultTextView = (TextView) this.findViewById(R.id.tv_scan_result);
        qrStrEditText = (EditText) this.findViewById(R.id.et_qr_string);
        qrImgImageView = (ImageView) this.findViewById(R.id.iv_qr_image);
        mCheckBox = (CheckBox) findViewById(R.id.logo);
        scanBarCodeButton = (Button) this.findViewById(R.id.btn_scan_barcode);
        generateQRCodeButton = (Button) this.findViewById(R.id.btn_add_qrcode);

    }

    @Override
    protected int providId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {
//获取输入框内容
        String m = ed_mobile.getText().toString();
        String p = password.getText().toString();
        presenter.login(m,p);
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onFaild(final String error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,"登录失败"+error,Toast.LENGTH_SHORT).show();
            }
        });
    }


    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

//点击跳转注册页面
    public void doreg(View view) {
        Intent it = new Intent(LoginActivity.this,RegActivity.class);
        startActivity(it);
    }


//点击生成二维码



}
