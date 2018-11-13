package com.aige.cuco.dagger2demo.imp.di.module;

import com.aige.cuco.dagger2demo.mvp.contract.UserCtract;
import com.aige.cuco.dagger2demo.mvp.model.UserModel;

import dagger.Module;
import dagger.Provides;
@Module
public class UserModule {
    private UserCtract.View view;

    public UserModule(UserCtract.View view) {
        this.view = view;
    }

    @Provides
    UserCtract.Model provideUserModel(UserModel model) {
        return model;
    }
    @Provides
    UserCtract.View provideUserView() {
        return this.view;
    }
}
