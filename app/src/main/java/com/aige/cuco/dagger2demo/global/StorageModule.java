package com.aige.cuco.dagger2demo.global;

import android.app.Application;

import com.aige.cuco.dagger2demo.global.storage.FileCache;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @Provides
    FileCache provideFileCache(Application application) {
        return new FileCache(application);
    }
}
