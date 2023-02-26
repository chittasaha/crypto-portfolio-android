package com.technobees.crypfolio.service.impl

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object CryptoServiceImpl {

    val baseUrl = "http://192.168.1.162:8080/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}