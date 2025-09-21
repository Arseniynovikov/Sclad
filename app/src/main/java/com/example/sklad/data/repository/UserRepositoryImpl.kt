package com.example.sklad.data.repository

import com.example.sklad.data.database.dao.UserDao
import com.example.sklad.data.database.entities.User
import com.example.sklad.data.mappers.toDomain
import com.example.sklad.data.mappers.toEntity
import com.example.sklad.domain.models.UserModel
import com.example.sklad.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {

    override fun getAllUsers(): Flow<List<UserModel>> =
        userDao.getAll().map { list -> list.map { it.toDomain() } }

    override fun getUserByUsername(username: String): Flow<UserModel?> =
        userDao.getByUsername(username).map{ it?.toDomain() }


    override suspend fun addUser(user: UserModel){
        userDao.insert(user.toEntity())
    }

    override suspend fun updateUser(user: UserModel) {
        userDao.update(user.toEntity())
    }

    override suspend fun deleteUser(user: UserModel) {
        userDao.delete(user.toEntity())
    }
}