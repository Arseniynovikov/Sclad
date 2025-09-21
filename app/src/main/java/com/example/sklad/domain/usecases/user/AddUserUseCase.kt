package com.example.sklad.domain.usecases.user

import com.example.sklad.domain.models.UserModel
import com.example.sklad.domain.repository.UserRepository

class AddUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(user: UserModel){
        repository.addUser(user)
    }
}