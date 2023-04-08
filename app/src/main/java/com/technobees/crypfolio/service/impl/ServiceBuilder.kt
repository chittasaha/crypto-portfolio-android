package com.technobees.crypfolio.service.impl

import com.technobees.crypfolio.service.OkHttpRequestInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.addHeaderLenient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ServiceBuilder {

    fun build(baseUrl : String): Retrofit {

        return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())

                .client(OkHttpClient.Builder()
                    .callTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .addInterceptor(OkHttpRequestInterceptor())
                    .build()
                )
                .build()
    }
}