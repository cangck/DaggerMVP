package com.aige.cuco.dagger2demo.global;

import android.app.Application;

import com.aige.cuco.dagger2demo.global.storage.FileCache;
import com.aige.cuco.dagger2demo.manager.IRepositoryManager;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {BackendModule.class, StorageModule.class, FrontendModule.class})
public interface AppComponent {

    Application application();

    FileCache getFileCache();

    Gson gson();

    IRepositoryManager repositoryManager();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        Builder frontendModule(FrontendModule frontendModule);

        //        Builder backendModule(BackendModule backendModule);
        Builder storageModule(StorageModule storageModule);

        AppComponent build();
    }
}
