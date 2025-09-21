package com.example.sklad.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sklad.data.database.entities.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions WHERE warehouseId = :warehouseId ORDER BY date DESC")
    fun getByWarehouse(warehouseId: Int): Flow<List<Transaction>>

    @Insert
    suspend fun insert(transaction: Transaction)

    @Query("SELECT * FROM transactions WHERE productId = :productId ORDER BY date DESC")
    fun getByProduct(productId: Int): Flow<List<Transaction>>
}
