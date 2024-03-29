package com.technobees.crypfolio.ui


//import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
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
import androidx.compose.material.pullrefresh.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.*

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
@Preview
fun CoinList(){
    val viewModel = viewModel<CoinListViewModel>()
    val state by viewModel.coins.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val pullRefreshState = rememberPullRefreshState(refreshing = isLoading, { viewModel.loadPrices() })


    Box(Modifier.pullRefresh(pullRefreshState)) {

        LazyColumn(verticalArrangement = Arrangement.spacedBy(0.5.dp)) {
            stickyHeader()
            {
                Row(
                    modifier = Modifier
                        //.border(width = 1.dp, color = Color.White)
                        .fillMaxWidth()
                        //.height(height = 100.dp)
                        //.padding(all = 3.dp)

                ) {
                    val headerModifier = Modifier
                        .fillMaxWidth(fraction = .25f)
                        .background(Color.Gray, RectangleShape)
                        .weight(.25f)

                    val headerTextStyle = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp

                    )

                    ClickableText(
                        text = AnnotatedString("Coin"),
                        modifier = headerModifier,

                        onClick = { offset ->
                            viewModel.sort(CryptoCoin::Name.name)
                        },
                        style = headerTextStyle
                    )
                    //MText(text = "Amount", modifier = headerModifier)
                    ClickableText(
                        text = AnnotatedString("Price"),
                        modifier = headerModifier,
                        onClick = { offset ->
                            viewModel.sort(CryptoCoin::Price.name)
                        },
                        style = headerTextStyle
                    )
                    ClickableText(
                        text = AnnotatedString("MCap."),
                        modifier = headerModifier,
                        onClick = { offset ->
                            viewModel.sort(CryptoCoin::MarketCapital.name)
                        },
                        style = headerTextStyle
                    )
                    ClickableText(
                        text = AnnotatedString("Change"),
                        modifier = headerModifier,
                        onClick = { offset ->
                            viewModel.sort(CryptoCoin::PriceChangePercent24Hours.name)
                        },
                        style = headerTextStyle
                    )
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
    val cellModifier = Modifier
        .fillMaxWidth(fraction = .25f)
        .padding(all = 3.dp)
        //.border(1.dp,Color.Red, RectangleShape)


    Row(
        modifier = Modifier
            .border(width = 1.dp, color = Color.Gray)
            //.background(color = MyThemeColor.Green80)
            .fillMaxWidth()
            .height(height = 35.dp)
    ) {

        Box(
            modifier = cellModifier.weight(.25f)
        ) {

            val annotedString = buildAnnotatedString {
                appendInlineContent("symbol_img")
                append(coin.Symbol.uppercase())
            }

            val inlineContentMap = mapOf(
                "symbol_img" to InlineTextContent(
                    Placeholder(20.sp, 20.sp, PlaceholderVerticalAlign.Center)
                ){
                    AsyncImage(
                        model = coin.IconUrl,
                        contentDescription = coin.Symbol,
                        modifier = Modifier
                            .clip(CircleShape)
                            .width(30.dp)
                            .height(30.dp)
                            .padding(3.dp),
                        contentScale = ContentScale.Crop,
                    )
                }
            )


            MText(annotedString, modifier = Modifier.align(
                Alignment.CenterStart), inlineContent = inlineContentMap
            )
        }
        MText(text = String.format("%.3f",coin.Price), modifier = cellModifier.weight(.25f))
        MText(text = String.format("%.3fBn",coin.MarketCapital/1000000000),modifier = cellModifier.weight(.25f))
        MText(text = String.format("%.2f%s",coin.PriceChangePercent24Hours, "%"), modifier = cellModifier.weight(.25f))
    }
}