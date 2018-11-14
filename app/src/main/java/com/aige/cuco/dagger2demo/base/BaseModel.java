package com.aige.cuco.dagger2demo.base;

import com.aige.cuco.dagger2demo.imp.IModel;
import com.aige.cuco.dagger2demo.manager.IRepositoryManager;

/**
 * 主要提供网络三级数据处理
 */
public class BaseModel implements IModel {
    /**
     * 在模型层负责网络请求，以及数据缓存层
     */
    protected IRepositoryManager mRepositorManager;

    /**
     * 在构建模型是注入仓库管理类
     *
     * @param mRepositorManager
     */
    public BaseModel(IRepositoryManager mRepositorManager) {
        this.mRepositorManager = mRepositorManager;
    }


    @Override
    public void onDestroy() {
        mRepositorManager = null;
    }
}
