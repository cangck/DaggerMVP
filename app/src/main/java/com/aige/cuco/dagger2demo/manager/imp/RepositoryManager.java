package com.aige.cuco.dagger2demo.manager.imp;

import android.app.Application;
import android.content.Context;

import com.aige.cuco.dagger2demo.manager.IRepositoryManager;
import com.aige.cuco.dagger2demo.manager.storage.Cache;
import com.aige.cuco.dagger2demo.manager.storage.CacheType;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.internal.RxCache;
import retrofit2.Retrofit;

/**
 * @package com.aige.cuco.dagger2demo.manager.imp
 * @fileName RepositoryManager
 * @date 2018/11/14
 * @describe
 * @email shenzhencuco@gmail
 */
public class RepositoryManager implements IRepositoryManager {
    @Inject
    Lazy<Retrofit> mRetrofit;
    @Inject
    Lazy<RxCache> mRxCache;
    @Inject
    Application application;
    @Inject
    Cache.Factory mCachefactory;

    @Inject
    public RepositoryManager() {
    }

    private Cache<String, Object> mRetrofitServiceCache;
    private Cache<String, Object> mCacheServiceCache;


    @Override
    public <T> T obtainRetrofitService(Class<T> serviceClass) {
        return createWrapperService(serviceClass);
    }

    private <T> T createWrapperService(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class[]{serviceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                if (method.getReturnType() == Observable.class) {
                    return Observable.defer(new Callable<ObservableSource<?>>() {
                        @Override
                        public ObservableSource<?> call() throws Exception {
                            T retrofitService = getRetrofitService(serviceClass);
                            return ((Observable) getRetrofitMethod(serviceClass, method)
                                    .invoke(retrofitService, method))
                                    .subscribeOn(Schedulers.io());
                        }
                    }).subscribeOn(Schedulers.io());
                }

                T service = getRetrofitService(serviceClass);

                return getRetrofitMethod(service, method).invoke(service, args);
            }
        });
    }

    /**
     * 根据传入的Class获取对应的Retrofit service
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public <T> T getRetrofitService(Class<T> serviceClass) {
        if (mRetrofitServiceCache == null) {
            mRetrofitServiceCache = mCachefactory.build(CacheType.RETROFIT_SERVICCE_CACHE);
        }
        Preconditions.checkNotNull(mRetrofitServiceCache, "Cannot return null from a Cache.Factory#build(int) method\"");
        T retrofiteService = (T) mRetrofitServiceCache.get(serviceClass.getCanonicalName());
        if (retrofiteService == null) {
            retrofiteService = mRetrofit.get().create(serviceClass);
            mRetrofitServiceCache.put(serviceClass.getCanonicalName(), retrofiteService);
        }
        return retrofiteService;
    }

    /**
     * 通过方法名来查找对应的方法
     *
     * @param service
     * @param method
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     */
    public <T> Method getRetrofitMethod(T service, Method method) throws NoSuchMethodException {
        return service.getClass().getMethod(method.getName(), method.getParameterTypes());
    }

    @Override
    public <T> T obtainCacheService(Class<T> cache) {
        if (mCacheServiceCache == null) {
            mCacheServiceCache = mCachefactory.build(CacheType.CACHE_SERVICE_CACHE);
        }

        Preconditions.checkNotNull(mCacheServiceCache, "Cannot return null from a Cache.Factory");
        T cacheService = (T) mCacheServiceCache.get(cache.getCanonicalName());
        if (cacheService == null) {
            cacheService = mRxCache.get().using(cache);
            mCacheServiceCache.put(cache.getCanonicalName(), cacheService);
        }
        return cacheService;
    }

    @Override
    public void clearAllCache() {
        mRxCache.get().evictAll().subscribe();
    }

    @Override
    public Context getContext() {
        return application;
    }
}
