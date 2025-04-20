package com.example.mobileup_trainee_test_task.presentation.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mobileup_trainee_test_task.presentation.Screen
import com.example.mobileup_trainee_test_task.presentation.event.RegistrationEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreen(
    navController: NavHostController,
    viewModel: RegistrationViewModel = koinViewModel()
) {
    val state = viewModel.state.value

    // Обработка навигации после успешной регистрации
    LaunchedEffect(state.shouldNavigate) {
        state.shouldNavigate?.let { screen ->
            navController.navigate(screen.route) {
                popUpTo(Screen.RegistrationScreen.route) { inclusive = true }
            }
            viewModel.onEvent(RegistrationEvent.ResetNavigation)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Заголовок
        Text(
            text = "Registration",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Поле email
        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onEvent(RegistrationEvent.EmailChanged(it)) },
            label = { Text("Email") },
            isError = state.emailError != null,
            modifier = Modifier.fillMaxWidth()
        )
        state.emailError?.let {
            ErrorText(text = it)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Поле пароля
        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onEvent(RegistrationEvent.PasswordChanged(it)) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = state.passwordError != null,
            modifier = Modifier.fillMaxWidth()
        )
        state.passwordError?.let {
            ErrorText(text = it)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Подтверждение пароля
        OutlinedTextField(
            value = state.confirmPassword,
            onValueChange = { viewModel.onEvent(RegistrationEvent.ConfirmPasswordChanged(it)) },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = state.confirmPasswordError != null,
            modifier = Modifier.fillMaxWidth()
        )
        state.confirmPasswordError?.let {
            ErrorText(text = it)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка регистрации
        Button(
            onClick = { viewModel.onEvent(RegistrationEvent.Submit) },
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text("Register")
            }
        }

        // Ошибка аутентификации
        state.authError?.let {
            Spacer(modifier = Modifier.height(8.dp))
            ErrorText(text = it)
        }

        // Ссылка на логин
        TextButton(
            onClick = { navController.navigate(Screen.LoginScreen.route) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Already have an account? Sign In")
        }
    }
}

@Composable
private fun ErrorText(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
    )
}