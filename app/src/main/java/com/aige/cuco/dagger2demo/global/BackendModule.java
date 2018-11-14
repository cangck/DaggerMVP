package com.aige.cuco.dagger2demo.global;

import android.app.Application;
import android.support.annotation.NonNull;

import com.aige.cuco.dagger2demo.manager.IRepositoryManager;
import com.aige.cuco.dagger2demo.manager.imp.RepositoryManager;
import com.aige.cuco.dagger2demo.manager.storage.Cache;
import com.aige.cuco.dagger2demo.manager.storage.CacheType;
import com.aige.cuco.dagger2demo.manager.storage.IntelligentCache;
import com.aige.cuco.dagger2demo.manager.storage.LruCache;
import com.aige.cuco.dagger2demo.utils.DataHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

@Module
public abstract class BackendModule {


    @Singleton
    @Provides
    static Gson provideGson(Application application) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Singleton
    @Provides
    static File provideRxCacheDirectory(Application application) {
        return DataHelper.getCacheFile(application);
    }



    @Binds
    abstract IRepositoryManager bindRepositoryManager(RepositoryManager repositoryManager);
}
