package com.example.sklad.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sklad.data.database.entities.Warehouse
import kotlinx.coroutines.flow.Flow

@Dao
interface WarehouseDao {
    @Query("SELECT * FROM warehouses")
    fun getAll(): Flow<List<Warehouse>>

    @Insert
    suspend fun insert(warehouse: Warehouse)

    @Update
    suspend fun update(warehouse: Warehouse)

    @Delete
    suspend fun delete(warehouse: Warehouse)
}