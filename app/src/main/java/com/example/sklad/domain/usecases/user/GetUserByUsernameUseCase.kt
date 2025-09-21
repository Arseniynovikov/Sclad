package com.example.sklad.domain.usecases.user

import com.example.sklad.domain.models.UserModel
import com.example.sklad.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserByUsernameUseCase(private val repository: UserRepository) {
    operator fun invoke(username: String): Flow<UserModel?> = repository.getUserByUsername(username)
}