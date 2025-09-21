package com.example.sklad.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(entity = Product::class, parentColumns = ["id"], childColumns = ["productId"]),
        ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"]),
        ForeignKey(entity = Warehouse::class, parentColumns = ["id"], childColumns = ["warehouseId"])
    ],
    indices = [Index("productId"), Index("userId"), Index("warehouseId")]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: Int,
    val warehouseId: Int,
    val type: TransactionType,
    val quantity: Int,
    val date: Long,
    val note: String?,
    val userId: Int
)
