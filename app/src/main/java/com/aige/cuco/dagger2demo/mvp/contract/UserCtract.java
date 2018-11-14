package com.aige.cuco.dagger2demo.mvp.contract;

import com.aige.cuco.dagger2demo.imp.IModel;
import com.aige.cuco.dagger2demo.imp.IView;
import com.aige.cuco.dagger2demo.mvp.User;

import java.util.List;

import io.reactivex.Observable;

public interface UserCtract {

    interface View extends IView {

    }

    interface Model extends IModel {
        Observable<List<User>> getUser(int lastIdQuery, boolean update);

        List<User> getUsers();
    }

}
