package com.example.sklad.domain.models

enum class UserRoleModel { ADMINISTRATOR, MANAGER, CLERK }

data class UserModel(
    val id: Int,
    val username: String,
    val password: String,
    val role: UserRoleModel,
    val warehouseId: Int? = null
)