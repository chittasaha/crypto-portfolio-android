package com.technobees.crypfolio.ui


//import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
//import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.technobees.crypfolio.data.CryptoCoin
import com.technobees.crypfolio.ui.viewmodel.CoinListViewModel
import androidx.compose.material3.Text as MText
import com.technobees.crypfolio.ui.theme.Color as MyThemeColor
import androidx.compose.material.pullrefresh.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun CoinList(){
    val viewModel = viewModel<CoinListViewModel>()
    val state by viewModel.coins.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val pullRefreshState = rememberPullRefreshState(refreshing = isLoading, { viewModel.loadPrices() })


    Box(Modifier.pullRefresh(pullRefreshState)) {
        val headerModifier = Modifier
            .fillMaxWidth(fraction = .25f)
            .padding(all = 5.dp)


        LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            stickyHeader()
            {
                Row(
                    modifier = Modifier
                        .border(width = 1.dp, color = Color.White)
                        //.background(color = Color.Green)
                        .fillMaxWidth()
                        .height(height = 35.dp)

                ) {
                    ClickableText(text = AnnotatedString("Coin"), modifier = headerModifier, onClick = { offset ->
                            viewModel.sort(CryptoCoin::Name.name)
                            //Log.d("ClickableText", "$offset -th character is clicked.")
                    })
                    //MText(text = "Amount", modifier = headerModifier)
                    ClickableText(text = AnnotatedString("Price"), modifier = headerModifier, onClick = { offset ->
                            viewModel.sort(CryptoCoin::Price.name)

                    })
                    ClickableText(text = AnnotatedString("MarketCapital"),modifier = headerModifier, onClick = {
                        offset ->
                        viewModel.sort(CryptoCoin::MarketCapital.name)
                    })
                    ClickableText(text = AnnotatedString("Change"),modifier = headerModifier, onClick = {
                            offset ->
                        viewModel.sort(CryptoCoin::LastChangeIn24Hours.name)
                    })
                    //MText(text = "Total", modifier = headerModifier)
                    /*MText(text = "Change", modifier = headerModifier)
                    MText(text = "MarketCapital",modifier = headerModifier)*/
                }
            }

            /*
            item {

                if(state.isEmpty()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
            }*/
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
            //.border(width = 1.dp, color = MyThemeColor.Green20)
            //.background(color = MyThemeColor.Green80)
            .fillMaxWidth()
            .height(height = 35.dp)
    ) {
        MText(text = coin.Name, modifier = rowModifier)
        MText(text = String.format("%.3f",coin.Price), modifier = rowModifier)
        MText(text = String.format("%.3f",coin.MarketCapital/1000000000),modifier = rowModifier)
        MText(text = String.format("%.2f",coin.LastChangeIn24Hours), modifier = rowModifier)
    }
}