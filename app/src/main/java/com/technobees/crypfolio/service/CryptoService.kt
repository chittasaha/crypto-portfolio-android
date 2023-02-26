package com.technobees.crypfolio.service

import com.technobees.crypfolio.data.CryptoCoin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CryptoService {
    @POST("prices")
    suspend fun getPrices(@Body ids: String) : Response<List<CryptoCoin>>
}