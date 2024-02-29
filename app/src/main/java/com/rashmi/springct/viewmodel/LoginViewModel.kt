package com.rashmi.springct.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rashmi.springct.usecase.LoginUseCase
import com.rashmi.springct.model.LoginCredentials
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel() : ViewModel() {


    private val _isLoginSuccess = MutableLiveData<Boolean>()
    val isLoginSuccess: LiveData<Boolean> = _isLoginSuccess

    fun validateCredentials(creds: LoginCredentials) {
        val useCase = LoginUseCase()
        CoroutineScope(Dispatchers.IO).launch {
            val result = useCase.validateUserCredentials(creds)
            withContext(Dispatchers.Main) {
                _isLoginSuccess.value = result
            }
        }
    }

}