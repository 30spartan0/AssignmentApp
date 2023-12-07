package com.example.assignmentapp.network

import com.example.assignmentapp.responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ) : LoginResponse
}