package com.example.assignmentapp.repository

import com.example.assignmentapp.network.AuthApi

class AuthRepository(
    private val api: AuthApi
) : BaseRepository() {

    suspend fun login(
        username: String,
        password: String
    ) = safeApiCall { api.login(username, password) }
}