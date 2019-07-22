package com.daniel.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface INetworkFactory {
    fun httpClient(baseUrl: String): Retrofit
    fun getOkHttpClient(): OkHttpClient
}