package com.example.sklad.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "warehouses")
data class Warehouse(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val location: String?,
    val description: String?
)