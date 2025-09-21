package com.example.sklad.data.mappers

import com.example.sklad.data.database.Converters
import com.example.sklad.data.database.entities.Product
import com.example.sklad.data.database.entities.User
import com.example.sklad.data.database.entities.UserRole
import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.models.UserModel
import com.example.sklad.domain.models.UserRoleModel

fun User.toDomain(): UserModel {
    val role = try {
        UserRoleModel.valueOf(role.name)
    }catch (e: IllegalArgumentException) {
        UserRoleModel.CLERK
    }
    return UserModel(id, username, password, role, warehouseId)
}

fun UserModel.toEntity(): User {
    return User(
        id,
        username,
        password,
        UserRole.valueOf(role.name),
        warehouseId
    )
}