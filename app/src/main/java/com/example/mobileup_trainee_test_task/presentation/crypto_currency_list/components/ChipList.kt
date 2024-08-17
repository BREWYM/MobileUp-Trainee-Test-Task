package com.example.mobileup_trainee_test_task.presentation.crypto_currency_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobileup_trainee_test_task.common.Currency

@Composable
fun ChipList(
    modifier: Modifier = Modifier,
    currencies: List<Currency>,
    onCurrencyChange: (Currency) -> Unit = {}
) {
    LazyRow(
        modifier = modifier.padding(start = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        items(currencies) {
            Chip(
                currency = it,
                currencyChange = onCurrencyChange
            )
        }
    }

}