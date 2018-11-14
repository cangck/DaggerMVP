package com.aige.cuco.dagger2demo.global;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class FrontendModule {

    @Provides
    Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl("127.0.0.1").build();
    }
}
