package com.example.sklad.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    foreignKeys = [
        ForeignKey(entity = Warehouse::class, parentColumns = ["id"], childColumns = ["warehouseId"])
    ],
    indices = [Index("warehouseId")]
)
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val password: String,
    val role: UserRole,
    val warehouseId: Int? // null если доступ ко всем складам
)
