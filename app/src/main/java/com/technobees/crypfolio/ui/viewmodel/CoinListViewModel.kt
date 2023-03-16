package com.technobees.crypfolio.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technobees.crypfolio.data.CryptoCoin
import com.technobees.crypfolio.service.CryptoService
import com.technobees.crypfolio.service.impl.ServiceBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoinListViewModel : ViewModel() {

    private val _coins = MutableStateFlow(emptyList<CryptoCoin>())
    val coins: StateFlow<List<CryptoCoin>>
        get() = _coins

    private val _orderBy = MutableStateFlow(CryptoCoin::LastChangeIn24Hours.name)
    private val _direction = MutableStateFlow("DESC")

    private val _isLoading = MutableStateFlow(false)
    var isLoading = _isLoading.asStateFlow()

    private val _crptoServiceBaseUrl: String = "http://192.168.1.162:8080/"
    private val _cyptoService : CryptoService
        get() = ServiceBuilder.build(_crptoServiceBaseUrl).create(CryptoService::class.java)

    fun loadPrices(){
        viewModelScope.launch {
            Log.d("OrderBy", _orderBy.value)
            val response = _cyptoService.getPrices(ids = "cardano,acala,altair,altura,astar,avalanche-2,centrifuge,coti,curve-dao-token,polkadot,efinity,ethereum,fantom,moonbeam,kilt-protocol,kintsugi,calamari-network,kusama,decentraland,moonriver,harmony,parallel-finance,the-sandbox,shiden,bitcoin,pha,interlay,litentry,nodle-network,origintrail,unique-network,polkadex,bifrost-native-coin")
            //val response = _cyptoService.getPrices(ids = "cardano,acala")
            if(response.isSuccessful){
                _coins.value = response.body()!!.toList().sortedByDescending { c -> c.MarketCapital }
            }
        }
    }

    fun sort(orderBy: String){

        Log.d("Sort By", orderBy)

        if(orderBy == _orderBy.value){
            toggleDirection()
            Log.d("Toggle:", _orderBy.value)
            //Log.d("Direction:", _direction.value)
        }
        else{
            _orderBy.value = orderBy
        }
        if(_direction.value == "DESC"){
            when (orderBy){
                "Name" -> {
                    _coins.value = _coins.value.sortedByDescending { c -> c.Name }
                }
                "Price" -> {
                    _coins.value = _coins.value.sortedByDescending { c -> c.Price }
                }
                "MarketCapital" -> {
                    _coins.value = _coins.value.sortedByDescending { c -> c.MarketCapital }
                }
                "LastChangeIn24Hours" -> {
                    _coins.value = _coins.value.sortedByDescending { c -> c.LastChangeIn24Hours }
                }
            }

        }
        else{
            when (orderBy){
                "Name" -> {
                    _coins.value = _coins.value.sortedBy { c -> c.Name }
                }
                "Price" -> {
                    _coins.value = _coins.value.sortedBy { c -> c.Price }
                }
                "MarketCapital" -> {
                    _coins.value = _coins.value.sortedBy { c -> c.MarketCapital }
                }
                "LastChangeIn24Hours" -> {
                    _coins.value = _coins.value.sortedBy { c -> c.LastChangeIn24Hours }
                }
            }
        }
    }

    private fun toggleDirection()
    {
        if(_direction.value == "DESC" ){
            _direction.value = "ASC"
        }
        else{
            _direction.value = "DESC"
        }

        Log.d("Direction:", _direction.value)
    }

    init {
        loadPrices()
    }

}