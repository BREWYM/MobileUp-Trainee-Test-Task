package com.example.mobileup_trainee_test_task.presentation.crypto_currency_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mobileup_trainee_test_task.R
import com.example.mobileup_trainee_test_task.common.Currency
import com.example.mobileup_trainee_test_task.domain.models.CryptoCurrency

@Composable
fun CryptoCurrencyListItem(
    crypto: CryptoCurrency,
    currency: Currency,
    onItemClick: (CryptoCurrency) -> Unit
) {
    val priceChange = crypto.priceChangePercentage24h
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(crypto) }
            .padding(top=10.dp),
        horizontalArrangement = Arrangement.Start
    ){
        AsyncImage(
            model = crypto.image,
            contentDescription = "crypto's image",
            modifier = Modifier
                .padding(start = 10.dp)
                .requiredSize(50.dp)
                .align(Alignment.CenterVertically)
                .clip(CircleShape)
                ,
            placeholder = painterResource(id =  R.drawable.btc_logo),
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 20.dp),

        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 2.dp)


                ,
                horizontalAlignment = Alignment.Start,
                verticalArrangement =  Arrangement.SpaceBetween,
            ) {

                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.W700, color = Color.DarkGray )){
                            append("${crypto.name}\n")
                        }
                        withStyle(style = SpanStyle(fontWeight = FontWeight.W300, color = Color.Gray)){
                            append(crypto.symbol.uppercase())
                        }
                    },
//                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.DarkGray
                            )){
                            append("${currency.symbol} ${crypto.currentPrice}")
                        }
                    }

                )
                Text(

                    text = if (priceChange>=0) {
                        "+" + "${crypto.priceChangePercentage24h}%"
                    } else
                        "${crypto.priceChangePercentage24h}%"
                     ,
                    color = if (crypto.priceChangePercentage24h >= 0)
                        Color(0xFF2a9d8f)
                    else Color(0xFFeb5757),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

    }
    
}