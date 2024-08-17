package com.example.mobileup_trainee_test_task.presentation.crypto_currency_list.components


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mobileup_trainee_test_task.common.Currency

@Composable
fun Chip(modifier: Modifier = Modifier,
         currency: Currency,
         isSelected: Boolean = false,
         currencyChange: (Currency) -> Unit
         ) {
    Surface(

        modifier = modifier
            .padding(10.dp),
        shape = RoundedCornerShape(30.dp),
        color = if (isSelected) Color(0xFFfbefdc)
        else Color(0xFFe0e0e0)
        ) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                        currencyChange(currency)
                    }
                )
        ) {
            Text(
                text = currency.id.uppercase(),
                color = if (isSelected) Color(255, 171, 33)
                else Color(29, 29, 29),
                modifier = Modifier
                    .align(Alignment.CenterVertically)

            )

        }

    }

}