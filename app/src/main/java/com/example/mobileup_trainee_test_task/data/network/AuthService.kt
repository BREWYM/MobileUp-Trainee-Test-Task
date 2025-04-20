package com.example.mobileup_trainee_test_task.data.network

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

// data/network/AuthService.kt
interface AuthService {
    suspend fun login(email: String, password: String): Boolean
    suspend fun register(email: String, password: String): Boolean
}

// Реализация для Firebase
class FirebaseAuthService : AuthService {
    override suspend fun login(email: String, password: String): Boolean {
        return try {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            false
        }
    }
    override suspend fun register(email: String, password: String): Boolean {
        return try {
            Firebase.auth.createUserWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            false
        }
    }
}

// Реализация для моков (для тестов)
class MockAuthService : AuthService {
    override suspend fun login(email: String, password: String): Boolean {
        return email.isNotBlank() && password.length >= 6
    }
    override suspend fun register(email: String, password: String): Boolean {
        return email.isNotBlank() && password.length >= 6
    }
}