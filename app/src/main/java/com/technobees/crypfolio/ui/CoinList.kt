package com.technobees.crypfolio.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel;
import com.technobees.crypfolio.data.CryptoCoin
import com.technobees.crypfolio.ui.viewmodel.CoinListViewModel
import androidx.compose.material3.Text as MText
import com.technobees.crypfolio.ui.theme.Color as MyThemeColor;
import androidx.compose.material.pullrefresh.*;

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoinList(){
    val viewModel = viewModel<CoinListViewModel>()
    val state by viewModel.state.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val pullRefreshState = rememberPullRefreshState(refreshing = isLoading, { viewModel.loadPrices() })

    Box(Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn {
            if (state.isEmpty()) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
            }
            items(state) { coin: CryptoCoin ->
                CoinDetail(coin = coin)
            }
        }

        PullRefreshIndicator(isLoading, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}
@Composable
fun CoinDetail(coin: CryptoCoin){
    val rowModifier = Modifier
        .fillMaxWidth(fraction = .25f)
        .padding(all = 5.dp)

    Row(
        modifier = Modifier
            .border(width = 1.dp, color = MyThemeColor.Green20)
            .background(color = MyThemeColor.Green80)
            .fillMaxWidth()
            .height(height = 35.dp)
    ) {
        MText(text = coin.Name, modifier = rowModifier)
        MText(text = String.format("%.3f",coin.Price), modifier = rowModifier)
        MText(text = String.format("%.2f",coin.LastChangeIn24Hours), modifier = rowModifier)
        MText(text = String.format("%.2f",coin.MarketCapital),modifier = rowModifier)
    }
}