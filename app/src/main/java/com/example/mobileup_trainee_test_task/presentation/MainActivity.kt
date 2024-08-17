package com.example.mobileup_trainee_test_task.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobileup_trainee_test_task.presentation.crypto_currency_list.CryptoCurrencyListScreen
import com.example.mobileup_trainee_test_task.presentation.crypto_description.CryptoDescriptionScreen
import com.example.mobileup_trainee_test_task.presentation.theme.MobileUpTraineeTestTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileUpTraineeTestTaskTheme {

                Surface(
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController,
                        startDestination = Screen.CryptoCurrencyListScreen.route ){
                        composable(
                            route = Screen.CryptoCurrencyListScreen.route
                        ){
                            CryptoCurrencyListScreen(navController = navController)
                            Log.d("NavHost", Screen.CryptoCurrencyListScreen.route)
                        }

                        composable(
                            route = Screen.CryptoDescriptionScreen.route +"/{cryptoId}"

                        ){
                            CryptoDescriptionScreen()
                            Log.d("NavHost", Screen.CryptoDescriptionScreen.route)
                        }
                    }

                }


            }

        }
    }
}
