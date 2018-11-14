package com.aige.cuco.dagger2demo.manager;

import android.content.Context;

/**
 * @package com.aige.cuco.dagger2demo.manager
 * @fileName IRepositoryManager
 * @date 2018/11/14
 * @describe
 * @email shenzhencuco@gmail
 */
public interface IRepositoryManager {
    /**
     * 根据传入的Class获取对应的Retrofit service
     *
     * @param service
     * @param <T>
     * @return
     */
    <T> T obtainRetrofitService(Class<T> service);

    /**
     * 根据传入的Class获取对应的RxChae service
     *
     * @param cache
     * @param <T>
     * @return
     */
    <T> T obtainCacheService(Class<T> cache);

    /**
     * 清理所有的缓存
     */
    void clearAllCache();

    /**
     * 获取上下文对象
     *
     * @return
     */
    Context getContext();
}
