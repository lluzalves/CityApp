package com.app.daniel.app.data.network

import com.daniel.data.network.INetworkFactory
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkFactory : INetworkFactory {

    override fun httpClient(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(getOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(baseUrl)
            .build()
    }

    override fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(4, TimeUnit.MINUTES)
            .readTimeout(4, TimeUnit.MINUTES)
            .retryOnConnectionFailure(false)
            .build()
    }

}