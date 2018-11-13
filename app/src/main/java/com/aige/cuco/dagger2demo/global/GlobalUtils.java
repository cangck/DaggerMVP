package com.aige.cuco.dagger2demo.global;

import android.content.Context;

public class GlobalUtils {
    /**
     * 获取全局的AppComponent
     *
     * @param context
     * @return
     */
    public static AppComponent obtainAppComponent(Context context) {
        return ((DaggerApp) context.getApplicationContext()).getAppComponent();
    }
}
