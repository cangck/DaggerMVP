package com.aige.cuco.dagger2demo.mvp.presenter;

import android.util.Log;

import com.aige.cuco.dagger2demo.base.BasePresenter;
import com.aige.cuco.dagger2demo.mvp.bean.User;
import com.aige.cuco.dagger2demo.mvp.contract.UserCtract;

import java.util.List;

import javax.inject.Inject;

public class UserPresenter extends BasePresenter<UserCtract.View, UserCtract.Model> {
    private final static String TAG = UserPresenter.class.getSimpleName();

    @Inject
    public UserPresenter(UserCtract.Model model, UserCtract.View view) {
        super(model, view);
    }

    /**
     * 获取用户列表
     */
    public void getUser() {
        List<User> users = mModel.getUsers();
        for (User user : users) {
            Log.i(TAG, user.getName());
        }
    }
}
