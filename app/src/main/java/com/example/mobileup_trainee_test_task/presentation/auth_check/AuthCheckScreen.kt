package com.example.mobileup_trainee_test_task.presentation.auth_check

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.mobileup_trainee_test_task.domain.repositories.AuthRepository
import com.example.mobileup_trainee_test_task.presentation.Screen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthCheckScreen(
    navController: NavHostController,
    authRepository: AuthRepository // Получаем напрямую, а не через koinViewModel()
) {
    LaunchedEffect(Unit) {
        val isLoggedIn = Firebase.auth.currentUser?.reload() != null
        navController.navigate(
            if (isLoggedIn) Screen.CryptoCurrencyListScreen.route
            else Screen.LoginScreen.route
        ) {
            popUpTo(0) // Очищаем весь стек
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}