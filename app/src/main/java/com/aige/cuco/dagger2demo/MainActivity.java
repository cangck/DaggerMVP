package com.aige.cuco.dagger2demo;

import android.os.Bundle;

import com.aige.cuco.dagger2demo.base.BaseActivity;
import com.aige.cuco.dagger2demo.global.GlobalUtils;
import com.aige.cuco.dagger2demo.imp.di.component.DaggerUserComponent;
import com.aige.cuco.dagger2demo.imp.di.module.UserModule;
import com.aige.cuco.dagger2demo.mvp.contract.UserCtract;
import com.aige.cuco.dagger2demo.mvp.presenter.UserPresenter;

public class MainActivity extends BaseActivity<UserPresenter> implements UserCtract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerUserComponent.builder()
                .appComponent(GlobalUtils.obtainAppComponent(this))
                .userModule(new UserModule(this)).build().inject(this);

    }
}
