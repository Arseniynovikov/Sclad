package com.example.sklad.domain.usecases.user

import com.example.sklad.domain.models.UserModel
import com.example.sklad.domain.models.WarehouseModel
import com.example.sklad.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetAllUsersUseCase(private val repository: UserRepository) {
    operator fun invoke(): Flow<List<UserModel>> = repository.getAllUsers()
}