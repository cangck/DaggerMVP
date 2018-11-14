package com.aige.cuco.dagger2demo.global;

import android.app.Application;
import android.support.annotation.NonNull;

import com.aige.cuco.dagger2demo.global.storage.FileCache;
import com.aige.cuco.dagger2demo.manager.storage.Cache;
import com.aige.cuco.dagger2demo.manager.storage.CacheType;
import com.aige.cuco.dagger2demo.manager.storage.IntelligentCache;
import com.aige.cuco.dagger2demo.manager.storage.LruCache;
import com.google.gson.Gson;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

@Module
public class StorageModule {

    @Provides
    FileCache provideFileCache(Application application) {
            return new FileCache(application);
    }

    @Singleton
    @Provides
    Cache.Factory provideCacheFactory(Application application) {
        return new Cache.Factory() {
            @NonNull
            @Override
            public Cache build(CacheType type) {
                switch (type.getCacheTypeId()) {
                    case CacheType.EXTRAS_TYPE_ID:
                    case CacheType.ACTIVITY_CACHE_ID:
                    case CacheType.RETROFIT_SERVICE_CACHE_TYPE:
                        return new IntelligentCache(type.calculateCacheSize(application));
                    default:
                        return new LruCache(type.calculateCacheSize(application));
                }
            }
        };
    }


    @Provides
    RxCache provideRxCache(Application application, File cacheFile, Gson gson) {
        RxCache.Builder builder = new RxCache.Builder();
        return builder.persistence(cacheFile, new GsonSpeaker(gson));
    }
}
