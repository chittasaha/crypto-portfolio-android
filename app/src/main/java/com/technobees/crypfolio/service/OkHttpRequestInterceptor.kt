package com.technobees.crypfolio.service

import okhttp3.Interceptor
import okhttp3.Response

class OkHttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        request.newBuilder()
            .addHeader("ACCEPT","application/json")
            .addHeader("USER_AGENT", "okhttp")

        return chain.proceed(request)
    }

}