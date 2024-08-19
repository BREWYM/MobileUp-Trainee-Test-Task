package com.example.mobileup_trainee_test_task.presentation.crypto_currency_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobileup_trainee_test_task.R
import com.example.mobileup_trainee_test_task.common.Currency
import com.example.mobileup_trainee_test_task.presentation.Screen
import com.example.mobileup_trainee_test_task.presentation.crypto_currency_list.components.ChipList
import com.example.mobileup_trainee_test_task.presentation.crypto_currency_list.components.CryptoCurrencyListItem
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoCurrencyListScreen(
    navController: NavController,
    viewModel: CryptoCurrencyListViewModel = koinViewModel()
) {
    val state by viewModel.state

    val isRefreshing by viewModel.isRefreshing
    val lastSucceededData by viewModel.lastSucceededData
    val refreshError by remember {
        viewModel.refreshError
    }
    val snackBarState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()
    val refreshState = rememberPullToRefreshState()
    val onRefresh: () -> Unit = {
        scope.launch {
            viewModel.refreshData()
            refreshState.animateToHidden()

        }
    }

    Scaffold(
        modifier = Modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackBarState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = Color(235, 87, 87),
                    contentColor = Color.White,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
            }
        },
        topBar = {
            Surface(
                shadowElevation = 8.dp,
                modifier = Modifier
            ) {
                Column {
                    TopAppBar(
                        modifier = Modifier
                            .fillMaxWidth(),
                        title = {
                            Column(
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = "Список криптовалют",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.W500,
                                    modifier = Modifier
                                )
                            }
                        }
                    )
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                    ) {
                        ChipList(
                            modifier = Modifier
                                .fillMaxWidth(),
                            selectedCurrency = viewModel.currency.value,
                            currencies = Currency.entries,
                            onCurrencyChange = { viewModel.getCryptoByCurrency(it) }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->

        PullToRefreshBox(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            state = refreshState,
            isRefreshing = isRefreshing,
            onRefresh = onRefresh
        ) {
            LaunchedEffect(refreshError) {
                if (refreshError) {
                    scope.launch {
                        snackBarState.showSnackbar("Произошла ошибка при загрузке")
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            )
            {
                items(state.cryptos) { crypto ->
                    CryptoCurrencyListItem(
                        crypto = crypto,
                        currency = viewModel.currency.value,
                        onItemClick = {
                            navController.navigate(
                                Screen.CryptoDescriptionScreen.route
                                        + "/${crypto.id}/${crypto.name}"
                            )
                        }
                    )
                }

            }
            if (state.error.isNotBlank()) {
                if (state.afterRefresh && lastSucceededData.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    {
                        items(lastSucceededData) { crypto ->
                            CryptoCurrencyListItem(
                                crypto = crypto,
                                currency = viewModel.currency.value,
                                onItemClick = {
                                    navController.navigate(
                                        Screen.CryptoDescriptionScreen.route
                                                + "/${crypto.id}/${crypto.name}"
                                    )
                                }
                            )
                        }


                    }
                } else {

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.groupbtc_logo),
                            contentDescription = "logo",
                            modifier = Modifier
                                .padding(top = 211.dp)
                                .requiredSize(120.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = "Произошла какая-то ошибка :(\n" +
                                    "Попробуем снова?",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onError,
                            modifier = Modifier
                                .padding(top = 13.dp)
                                .align(Alignment.CenterHorizontally),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onError,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W400
                            )
                        )
                        Button(
                            onClick = { viewModel.getCryptoByCurrency(viewModel.currency.value) },
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier
                                .width(175.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 30.dp),
                        ) {
                            Text(text = "ПОПРОБОВАТЬ", fontSize = 16.sp)
                        }
                    }
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color(0xFFFF9F00)
                )
            }
        }
    }
}


