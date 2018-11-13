package com.aige.cuco.dagger2demo.global.storage;

import android.app.Application;
import android.util.Log;

public class FileCache {
    private static final String TAG = FileCache.class.getSimpleName();
    private Application application;

    public FileCache(Application application) {
        this.application = application;
        if (null == application) {
            throw new RuntimeException("Applicaton not null");
        }
        Log.i(TAG, "FileCache init success");
    }
}
