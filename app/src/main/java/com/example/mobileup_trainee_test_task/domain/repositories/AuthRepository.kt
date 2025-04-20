package com.example.mobileup_trainee_test_task.domain.repositories

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun login(email: String, password: String): Boolean
    suspend fun register(email: String, password: String): Boolean
    fun getCurrentUser(): FirebaseUser?
}