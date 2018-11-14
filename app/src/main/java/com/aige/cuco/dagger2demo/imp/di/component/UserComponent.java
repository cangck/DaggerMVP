package com.aige.cuco.dagger2demo.imp.di.component;

import com.aige.cuco.dagger2demo.MainActivity;
import com.aige.cuco.dagger2demo.annotation.ActivityScope;
import com.aige.cuco.dagger2demo.global.AppComponent;
import com.aige.cuco.dagger2demo.imp.di.module.UserModule;

import dagger.Component;

@ActivityScope
@Component(modules = {UserModule.class}, dependencies = AppComponent.class)
public interface UserComponent {
    void inject(MainActivity activity);
}
