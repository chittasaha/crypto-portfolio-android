package com.technobees.crypfolio.data

import com.google.gson.annotations.SerializedName

data class GetTokensRequest(
    @SerializedName("base_currency")
    val BaseCurrency : String,
    @SerializedName("ids")
    val Ids : Array<String>
)

