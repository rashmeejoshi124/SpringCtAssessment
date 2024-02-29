package com.rashmi.springct.usecase

import com.rashmi.springct.model.LoginCredentials

interface ILoginUseCase {

   suspend fun validateUserCredentials(creds: LoginCredentials): Boolean
}