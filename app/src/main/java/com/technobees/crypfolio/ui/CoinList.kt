package com.technobees.crypfolio.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel;
import com.technobees.crypfolio.data.CryptoCoin
import com.technobees.crypfolio.ui.viewmodel.CoinListViewModel
import androidx.compose.material3.Text as MText

@Composable
fun CoinList(){

    val viewModel = viewModel(modelClass = CoinListViewModel::class.java)
    val state by viewModel.state.collectAsState()
    LazyColumn {
        if(state.isEmpty()){
            item{
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }
        items(state){ coin: CryptoCoin ->
            CoinDetail(coin = coin)
        }
    }
}
@Composable
fun CoinDetail(coin: CryptoCoin){
    Row() {
        MText(text = coin.Name)
        MText(text = String.format("|%.4f",coin.Price))
        MText(text = String.format("|%.2f",coin.LastChangeIn24Hours))
        MText(text = String.format("|%.2f",coin.MarketCapital))

    }
}