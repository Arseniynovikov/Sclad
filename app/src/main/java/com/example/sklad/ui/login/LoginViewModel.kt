package com.example.sklad.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sklad.domain.models.UserModel
import com.example.sklad.domain.repository.UserRepository
import com.example.sklad.domain.usecases.user.GetUserByUsernameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LoginViewModel(private val getUserByUsernameUseCase: GetUserByUsernameUseCase) : ViewModel() {

    // Состояние для хранения текущего пользователя
    private val _user = MutableStateFlow<UserModel?>(null)
    val user: StateFlow<UserModel?> = _user.asStateFlow()

    // Функция загрузки пользователя по username
    fun loadUser(username: String) {
        viewModelScope.launch {
            getUserByUsernameUseCase(username) // вызов выглядит как вызов функции
                .catch { e ->
                    // Обработка ошибок, например:
                    Log.e("UserViewModel", "Error loading user", e)
                }
                .collect { fetchedUser ->
                    _user.value = fetchedUser
                }
        }
    }
}