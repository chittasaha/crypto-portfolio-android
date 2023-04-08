package com.technobees.crypfolio.service

import com.technobees.crypfolio.data.CryptoCoin
import com.technobees.crypfolio.data.GetTokensRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Currency

interface CryptoService {
    //@POST("tokens")
    //suspend fun getTokens(@Body req: GetTokensRequest) : Response<List<CryptoCoin>>
    @GET("api/v3/coins/markets")
    suspend fun getTokens(@Query("vs_currency") currency: String, @Query("ids") ids: String,
                @Query("order") orderby : String = "market_cap_desc", @Query("per_page") perPage: Int = 5000,
                @Query("page") page: Int = 1
    ) : Response<List<CryptoCoin>>
}