package com.example.sklad.domain.repository

import com.example.sklad.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<List<UserModel>>
    fun getUserByUsername(username: String): Flow<UserModel?>
    suspend fun addUser(user: UserModel)
    suspend fun updateUser(user: UserModel)
    suspend fun deleteUser(user: UserModel)
}