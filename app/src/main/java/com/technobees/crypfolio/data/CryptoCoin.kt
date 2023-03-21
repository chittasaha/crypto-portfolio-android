package com.technobees.crypfolio.data

import com.google.gson.annotations.SerializedName

data class CryptoCoin(
    @SerializedName("id")
    val Id: String,
    @SerializedName("name")
    val Name: String,
    @SerializedName("current_price")
    val Price: Float,
    @SerializedName("market_cap")
    val MarketCapital: Float,
    @SerializedName("all_time_high")
    val AllTimeHigh : Float,
    @SerializedName("all_time_low")
    val AllTimeLow : Float,
    @SerializedName("ath_date")
    val AllTimeHighDate : String,
    @SerializedName("atl_date")
    val AllTimeLowDate : String,
    @SerializedName("ath_change_percent")
    val AllTimeHighChangePercent : Float,
    @SerializedName("atl_change_percent")
    val AllTimeLowChangePercent : Float,
    @SerializedName("cir_supply")
    val CirculatorySupply : Float,
    @SerializedName("total_supply")
    val TotalSupply : Float,
    @SerializedName("max_supply")
    val MaxSupply : Float,
    @SerializedName("change_last_24_hours")
    val LastChangeIn24Hours : Float,
    @SerializedName("icon_url")
    val IconUrl : String,
)
