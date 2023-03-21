package com.technobees.crypfolio.service

import com.technobees.crypfolio.data.CryptoCoin
import com.technobees.crypfolio.data.GetTokensRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CryptoService {
    @POST("tokens")
    suspend fun getTokens(@Body req: GetTokensRequest) : Response<List<CryptoCoin>>
}