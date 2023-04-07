package com.technobees.crypfolio.data

import com.google.gson.annotations.SerializedName

data class CryptoCoin(
    @SerializedName("id")
    val Id: String,
    @SerializedName("symbol")
    val Symbol: String,
    @SerializedName("name")
    val Name: String,
    @SerializedName("image")
    val IconUrl : String,
    @SerializedName("current_price")
    val Price: Float,
    @SerializedName("market_cap")
    val MarketCapital: Float,
    @SerializedName("market_cap_rank")
    val Rank: Int,
    @SerializedName("total_volume")
    val TotalVolume: Float,
    @SerializedName("high_24h")
    val High24Hours : Float,
    @SerializedName("low_24h")
    val Low24Hours : Float,
    @SerializedName("price_change_percentage_24h")
    val PriceChangePercent24Hours : Float,
    @SerializedName("price_change_24h")
    val PriceChange24Hours : Float,
    @SerializedName("market_cap_change_24h")
    val MarketCapChange24Hours : Float,
    @SerializedName("circulating_supply")
    val CirculatorySupply : Float,
    @SerializedName("max_supply")
    val MaxSupply : Float,
    @SerializedName("total_supply")
    val TotalSupply : Float,
    @SerializedName("ath")
    val AllTimeHigh : Float,
    @SerializedName("ath_change_percentage")
    val AllTimeHighChangePercent : Float,
    @SerializedName("ath_date")
    val AllTimeHighDate : String,
    @SerializedName("atl")
    val AllTimeLow : Float,
    @SerializedName("atl_change_percent")
    val AllTimeLowChangePercent : Float,
    @SerializedName("atl_date")
    val AllTimeLowDate : String
)
