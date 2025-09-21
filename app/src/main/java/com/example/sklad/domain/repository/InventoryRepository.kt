package com.example.sklad.domain.repository

import com.example.sklad.domain.models.CategoryModel
import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.models.WarehouseModel
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {
    //Products
    fun getAllProducts(): Flow<List<ProductModel>>
    fun getProductsByWarehouse(warehouseId: Int): Flow<List<ProductModel>>
    suspend fun addProduct(product: ProductModel)
    suspend fun updateProduct(product: ProductModel)
    suspend fun deleteProduct(product: ProductModel)

    //Category
    fun getAllCategory(): Flow<List<CategoryModel>>
    suspend fun addCategory(category: CategoryModel)

    //Warehouse
    fun getAllWarehouses(): Flow<List<WarehouseModel>>
    suspend fun addWarehouse(warehouse: WarehouseModel)
    suspend fun updateWarehouse(warehouse: WarehouseModel)
    suspend fun deleteWarehouse(warehouse: WarehouseModel)
}