package com.aige.cuco.dagger2demo.global;

import android.app.Application;

import com.aige.cuco.dagger2demo.global.storage.FileCache;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {StorageModule.class, FrontendModule.class, BackendModule.class})
public interface AppComponent {
    Application application();

    FileCache getFileCache();

    @Component.Builder
    interface Builder {


        @BindsInstance
        Builder application(Application application);

        Builder frontendModule(FrontendModule frontendModule);

        Builder backendModule(BackendModule backendModule);

        AppComponent build();
    }
}
