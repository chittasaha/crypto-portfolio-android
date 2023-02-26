package com.technobees.crypfolio.data

import com.google.gson.annotations.SerializedName

data class CryptoCoin(
    @SerializedName("name")
    val Name: String,
    @SerializedName("price")
    val Price: Float,
    @SerializedName("base_price")
    val BaseCurrency : String,
    @SerializedName("change_last_24_hours")
    val LastChangeIn24Hours : Float,
    @SerializedName("market_capital")
    val MarketCapital : Float
)
