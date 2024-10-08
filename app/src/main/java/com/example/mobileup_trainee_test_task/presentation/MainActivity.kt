package com.example.mobileup_trainee_test_task.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CryptoCurrencyListScreen.route
                    ) {
                        composable(
                            route = Screen.CryptoCurrencyListScreen.route
                        ) {
                            CryptoCurrencyListScreen(navController = navController)
                            Log.d("NavHost", Screen.CryptoCurrencyListScreen.route)
                        }

                        composable(
                            route = Screen.CryptoDescriptionScreen.route
                                    + "/{cryptoId}" + "/{cryptoName}"
                            ,
                            arguments = listOf(
                                navArgument("cryptoId") {
                                    type = NavType.StringType
                                },
                                navArgument("cryptoName"){
                                    type = NavType.StringType
                                }
                            )

                        ) {
                            val cryptoId = it.arguments?.getString("cryptoId") ?: ""
                            val cryptoName = it.arguments?.getString("cryptoName") ?: ""
                            CryptoDescriptionScreen(
                                cryptoName = cryptoName,
                                cryptoId = cryptoId,
                                navigateBack = { navController.popBackStack() },
                            )
                            Log.d("NavHost", Screen.CryptoDescriptionScreen.route)
                        }
                    }

                }


            }

        }
    }
}
