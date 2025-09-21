package com.example.sklad.domain.usecases.user

import com.example.sklad.domain.models.UserModel
import com.example.sklad.domain.repository.UserRepository

class UpdateUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(user: UserModel){
        repository.updateUser(user)
    }
}