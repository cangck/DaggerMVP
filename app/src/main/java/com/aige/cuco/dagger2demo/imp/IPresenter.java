package com.aige.cuco.dagger2demo.imp;

public interface IPresenter {
    /**
     * 初始化
     */
    void onStart();

    /**
     * 销毁资源
     */
    void onDestroy();

}
