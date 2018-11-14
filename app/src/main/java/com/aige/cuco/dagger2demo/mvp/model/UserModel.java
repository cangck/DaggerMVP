package com.aige.cuco.dagger2demo.mvp.model;

import com.aige.cuco.dagger2demo.annotation.ActivityScope;
import com.aige.cuco.dagger2demo.base.BaseModel;
import com.aige.cuco.dagger2demo.manager.IRepositoryManager;
import com.aige.cuco.dagger2demo.mvp.bean.User;
import com.aige.cuco.dagger2demo.mvp.contract.UserCtract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class UserModel extends BaseModel implements UserCtract.Model {
    private List<User> mUsers = new ArrayList<>();

    /**
     * 在构建模型是注入仓库管理类
     *
     * @param mRepositorManager
     */
    @Inject
    public UserModel(IRepositoryManager mRepositorManager) {
        super(mRepositorManager);
    }

    @Override
    public Observable<List<User>> getUser(int lastIdQuery, boolean update) {
        return null;
    }

    @Override
    public List<User> getUsers() {

        User user;
        for (int i = 0; i < 5; i++) {
            user = new User();
            user.setName("name" + i);
            user.setAge(i * 5 + "");
            mUsers.add(user);
        }
        return mUsers;
    }
}
