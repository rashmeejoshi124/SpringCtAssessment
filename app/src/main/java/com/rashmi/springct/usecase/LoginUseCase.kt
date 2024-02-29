package com.rashmi.springct.usecase

import android.util.Log
import com.rashmi.springct.api.LoginService
import com.rashmi.springct.api.RetrofitBuilder
import com.rashmi.springct.model.LoginCredentials

class LoginUseCase : ILoginUseCase {
    override suspend fun validateUserCredentials(creds: LoginCredentials): Boolean {
        val apiService = RetrofitBuilder.retrofit.create(LoginService::class.java)

        val body = mapOf(
            "email" to creds.email,
            "password" to creds.password
        )
        val response = apiService.validateCredentials(
            body
        )
        Log.d("RASHMI", "validateUserCredentials: ${response.isSuccessful} - ${response.code()}")
        return response.code() == 200
    }
}