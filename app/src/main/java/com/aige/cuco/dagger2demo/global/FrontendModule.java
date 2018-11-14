package com.aige.cuco.dagger2demo.global;

import com.aige.cuco.dagger2demo.global.http.GlobalHttpHandler;
import com.aige.cuco.dagger2demo.global.http.ProgressManager;
import com.aige.cuco.dagger2demo.global.http.RequestInterceptor;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class FrontendModule {
    //    客户端超时
    private static final int TIME_OUT = 10;

    @Provides
    static Retrofit provideRetrofit(OkHttpClient client, Gson gson) {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("127.0.0.1")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));

        return builder.build();
    }

    /**
     * OkHttpClient 配置：
     * 1.超时
     * 2.添加拦截
     * 3.添加网络拦截
     * 4.认证
     * 5.缓存控制
     * 6.连接池
     * 7.连接Specs
     * 8.cookiejar
     * 9.dispatcher
     * 10.dns
     * 11.事件监听
     * 12.重定向
     * 13.ssl重定向
     * 14.主机名认证
     * 15.Intercepters
     * 16.Ping间隔
     * 17.protocots
     * 18.Proxy代理
     * 19.Proxy认证
     * 20.proxy代理选择器
     * 21.失败重试
     * 22.socketFactory
     * 23.sslsocketFactory
     *
     * @param interceptor
     * @param globalHttpHandler
     * @param executorService
     * @return
     */
    @Provides
    static OkHttpClient provideOkHttpClicent(RequestInterceptor interceptor, GlobalHttpHandler globalHttpHandler, ExecutorService executorService) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(interceptor);
        if (globalHttpHandler != null) {
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    return chain.proceed(globalHttpHandler.onHttpRequestBefore(chain, chain.request()));
                }
            });
        }
        if (executorService != null) {
            builder.dispatcher(new Dispatcher(executorService));
        }
        //使用一行代码监听 Retrofit／Okhttp 上传下载进度监听,以及 Glide 加载进度监听 详细使用方法查看 https://github.com/JessYanCoding/ProgressManager
        ProgressManager.getInstance().with(builder);
        //让 Retrofit 同时支持多个 BaseUrl 以及动态改变 BaseUrl. 详细使用请方法查看 https://github.com/JessYanCoding/RetrofitUrlManager
//        RetrofitUrlManager.getInstance().with(okhttpBuilder);
        return builder.build();
    }

    @Binds
    abstract Interceptor bindIntercepter(RequestInterceptor interceptor);
}
