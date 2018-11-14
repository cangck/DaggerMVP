package com.aige.cuco.dagger2demo.global;

import android.app.Application;
import android.content.Context;

public class DaggerApp extends Application {
    private static AppComponent mAppComponent;
    private static DaggerApp mDaggerApp;


    @Override
    protected void attachBaseContext(Context base) {
        mAppComponent = DaggerAppComponent.builder()
                .application(this)
                .storageModule(new StorageModule())
                .frontendModule(new FrontendModule()).build();
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mDaggerApp = this;

    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
