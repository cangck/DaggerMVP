package com.aige.cuco.dagger2demo.global.http;

import org.jetbrains.annotations.NotNull;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @package com.aige.cuco.dagger2demo.global.http
 * @fileName GlobalHttpHandler
 * @date 2018/11/14
 * @describe 全局拦截Http操作类
 * @email shenzhencuco@gmail
 */
public interface GlobalHttpHandler {
    Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response);

    Request onHttpRequestBefore(Interceptor.Chain chain, Request request);

    /**
     * 默认实现
     */
    GlobalHttpHandler EMPTY = new GlobalHttpHandler() {
        @NotNull
        @Override
        public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {
            return response;
        }

        @NotNull
        @Override
        public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
            return request;
        }
    };
}
