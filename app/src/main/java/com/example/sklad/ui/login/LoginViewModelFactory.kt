package com.example.sklad.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sklad.domain.usecases.user.GetUserByUsernameUseCase

class LoginViewModelFactory(
    private val getUserByUsernameUseCase: GetUserByUsernameUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(getUserByUsernameUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}