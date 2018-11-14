package com.aige.cuco.dagger2demo.base;

import com.aige.cuco.dagger2demo.imp.IModel;
import com.aige.cuco.dagger2demo.imp.IPresenter;
import com.aige.cuco.dagger2demo.imp.IView;

public class BasePresenter<V extends IView, M extends IModel> implements IPresenter {
    public M mModel;
    public V mRootView;

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mRootView = mView;
        onStart();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {
        this.mModel = null;
        this.mRootView = null;
    }
}
