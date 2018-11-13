package com.aige.cuco.dagger2demo.mvp.presenter;

import com.aige.cuco.dagger2demo.base.BasePresenter;
import com.aige.cuco.dagger2demo.mvp.contract.UserCtract;

import javax.inject.Inject;

public class UserPresenter extends BasePresenter<UserCtract.View, UserCtract.Model> {


    @Inject
    public UserPresenter(UserCtract.Model mModel, UserCtract.View mView) {
        super(mModel, mView);
    }
}
