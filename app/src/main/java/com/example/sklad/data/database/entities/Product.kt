package com.example.sklad.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "products",
    foreignKeys = [
        ForeignKey(entity = Category::class, parentColumns = ["id"], childColumns = ["categoryId"]),
        ForeignKey(entity = Warehouse::class, parentColumns = ["id"], childColumns = ["warehouseId"])
    ],
    indices = [Index("categoryId"), Index("warehouseId")]
)
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val barcode: String,
    val description: String?,
    val quantity: Int,
    val unit: String,
    val categoryId: Int,
    val warehouseId: Int
)
