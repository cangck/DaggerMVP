package com.aige.cuco.dagger2demo.global.rx;

import android.content.Context;

public interface ResponseErrorListener {
    void handleResponseError(Context context, Throwable t);

    ResponseErrorListener EMPTY = new ResponseErrorListener() {
        @Override
        public void handleResponseError(Context context, Throwable t) {


        }
    };
}
