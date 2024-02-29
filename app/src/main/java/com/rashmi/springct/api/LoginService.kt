package com.rashmi.springct.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginService {

    @POST("login")
    suspend fun validateCredentials(
        @Body body: Map<String, String>
    ): Response<ResponseBody>
}

