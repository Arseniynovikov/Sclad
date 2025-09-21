package com.example.sklad.data.repository

import com.example.sklad.data.database.dao.CategoryDao
import com.example.sklad.data.database.dao.ProductDao
import com.example.sklad.data.database.dao.UserDao
import com.example.sklad.data.database.dao.WarehouseDao
import com.example.sklad.data.database.entities.Category
import com.example.sklad.data.mappers.toDomain
import com.example.sklad.data.mappers.toEntity
import com.example.sklad.domain.models.CategoryModel
import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.models.UserModel
import com.example.sklad.domain.models.WarehouseModel
import com.example.sklad.domain.repository.InventoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class InventoryRepositoryImpl(
    private val productDao: ProductDao,
    private val categoryDao: CategoryDao,
    private val warehouseDao: WarehouseDao
) : InventoryRepository {

    //Product
    override fun getAllProducts(): Flow<List<ProductModel>> =
        productDao.getAll().map { list -> list.map { it.toDomain() } }

    override fun getProductsByWarehouse(warehouseId: Int): Flow<List<ProductModel>> =
        productDao.getByWarehouse(warehouseId).map { list -> list.map { it.toDomain() } }

    override suspend fun addProduct(product: ProductModel) {
        productDao.insert(product.toEntity())
    }

    override suspend fun updateProduct(product: ProductModel) {
        productDao.update(product.toEntity())
    }

    override suspend fun deleteProduct(product: ProductModel) {
        productDao.delete(product.toEntity())
    }

    //Category
    override fun getAllCategory(): Flow<List<CategoryModel>> =
        categoryDao.getAll().map { list -> list.map { it.toDomain() } }


    override suspend fun addCategory(category: CategoryModel) {
        categoryDao.insert(category.toEntity())
    }

    //Warehouse
    override fun getAllWarehouses(): Flow<List<WarehouseModel>> =
        warehouseDao.getAll().map {list -> list.map {it.toDomain()}}

    override suspend fun addWarehouse(warehouse: WarehouseModel) {
        warehouseDao.insert(warehouse.toEntity())
    }

    override suspend fun updateWarehouse(warehouse: WarehouseModel) {
        warehouseDao.update(warehouse.toEntity())
    }

    override suspend fun deleteWarehouse(warehouse: WarehouseModel) {
        warehouseDao.delete(warehouse.toEntity())
    }
}