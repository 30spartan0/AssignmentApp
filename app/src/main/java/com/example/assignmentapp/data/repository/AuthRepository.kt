package com.example.assignmentapp.data.repository

import com.example.assignmentapp.data.UserPreferences
import com.example.assignmentapp.data.network.AuthApi

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun login(
        username: String,
        password: String
    ) = safeApiCall { api.login(username, password) }

    suspend fun saveAuthToken(token: String){
        preferences.saveAuthToken(token)
    }
}