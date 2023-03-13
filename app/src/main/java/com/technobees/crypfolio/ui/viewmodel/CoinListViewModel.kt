package com.technobees.crypfolio.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technobees.crypfolio.data.CryptoCoin
import com.technobees.crypfolio.service.CryptoService
import com.technobees.crypfolio.service.impl.CryptoServiceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoinListViewModel : ViewModel() {

    private val _coins = MutableStateFlow(emptyList<CryptoCoin>())
    val coins: StateFlow<List<CryptoCoin>>
        get() = _coins


    private val _isLoading = MutableStateFlow(false)
    var isLoading = _isLoading.asStateFlow()

    private val _cyptoService : CryptoService
        get() = CryptoServiceImpl.getInstance().create(CryptoService::class.java)

    fun loadPrices(){
        viewModelScope.launch {
            val response = _cyptoService.getPrices(ids = "cardano,acala,altair,altura,astar,avalanche-2,centrifuge,coti,curve-dao-token,polkadot,efinity,ethereum,fantom,moonbeam,kilt-protocol,kintsugi,calamari-network,kusama,decentraland,moonriver,harmony,parallel-finance,the-sandbox,shiden,bitcoin,pha,interlay,litentry,nodle-network,origintrail,unique-network,polkadex,bifrost-native-coin")
            if(response.isSuccessful){
                _coins.value = response.body()!!.toList()
            }
        }
    }

    init {
        loadPrices()
    }

}