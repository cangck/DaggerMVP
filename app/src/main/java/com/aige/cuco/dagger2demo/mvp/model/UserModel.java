package com.aige.cuco.dagger2demo.mvp.model;

import com.aige.cuco.dagger2demo.annotation.ActivityScope;
import com.aige.cuco.dagger2demo.base.BaseModel;
import com.aige.cuco.dagger2demo.mvp.contract.UserCtract;

import javax.inject.Inject;

@ActivityScope
public class UserModel extends BaseModel implements UserCtract.Model {
    @Inject
    public UserModel() {
    }
}
