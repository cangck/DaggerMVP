package com.aige.cuco.dagger2demo.base;

import android.support.v7.app.AppCompatActivity;

import com.aige.cuco.dagger2demo.imp.IPresenter;

import javax.inject.Inject;

public class BaseActivity<P extends IPresenter> extends AppCompatActivity {
    @Inject
    P Presenter;
}
