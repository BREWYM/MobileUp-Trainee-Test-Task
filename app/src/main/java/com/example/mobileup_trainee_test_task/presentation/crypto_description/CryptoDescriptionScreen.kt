package com.example.mobileup_trainee_test_task.presentation.crypto_description


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mobileup_trainee_test_task.R
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoDescriptionScreen(
    cryptoId : String,
    viewModel: CryptoDescriptionViewModel = koinViewModel(),
    navigateBack: () -> Unit
) {
    val state = viewModel.state.value
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier
            .fillMaxWidth(),
        topBar = {
            Surface(shadowElevation = 8.dp) {
                TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    navigationIcon = {
                        IconButton(onClick = navigateBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Button to go back"
                            )
                        }
                    },
                    title = {
                        Row(
                            modifier = Modifier
                                .padding(start = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            state.crypto?.let { Text(text = it.name) }

                        }
                    }
                )


            }
        }
    ) {

            innerPadding ->


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (state.error.isBlank()&&!state.isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                )
                {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        AsyncImage(
                            model = state.crypto?.image,
                            contentDescription = "crypto's image",
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .clip(CircleShape),
//                            placeholder = painterResource(id = R.drawable.btc_logo),
                        )
                    }


                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.W500,
                                    color = Color.Black,
                                    fontSize = 20.sp
                                )
                            ) {
                                append("Описание\n")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.W400,
                                    color = Color.Black,
                                    fontSize = 16.sp
                                )
                            ) {
                                state.crypto?.description?.let {
                                    append(state.crypto.description + "\n")
                                }
                            }
                        },
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.W500,
                                    color = Color.Black,
                                    fontSize = 20.sp
                                )
                            ) {
                                append("Категории\n")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.W400,
                                    color = Color.Black,
                                    fontSize = 16.sp
                                )
                            ) {
                                state.crypto?.let {
                                    append(it.categories.joinToString(separator = ", "))
                                }
                            }
                        },
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 20.dp
                                )

                    )
                }
            }
            if (state.error.isNotBlank()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.btc_logo),
                        contentDescription = "logo",
                        modifier = Modifier
                            .padding(16.dp)
                            .requiredSize(100.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "Произошла какая-то ошибка :(\n" +
                                "Попробуем снова?",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onError,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .align(Alignment.CenterHorizontally),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onError,
                            fontSize = 16.sp
                        )

                    )


                    Button(
                        onClick = { coroutineScope.launch {
                            viewModel.refresh(cryptoId)
                            }
                        },
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 30.dp),

                        ) {
                        Text(text = "ПОПРОБОВАТЬ", fontSize = 16.sp)
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
