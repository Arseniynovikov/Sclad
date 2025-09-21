package com.example.sklad.domain.usecases.warehouse

import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.models.WarehouseModel
import com.example.sklad.domain.repository.InventoryRepository
import kotlinx.coroutines.flow.Flow

class GetAllWarehousesUseCase (private val repository: InventoryRepository) {
    operator fun invoke(): Flow<List<WarehouseModel>> = repository.getAllWarehouses()
}