package com.example.sklad.domain.usecases.warehouse

import com.example.sklad.data.database.entities.Warehouse
import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.models.WarehouseModel
import com.example.sklad.domain.repository.InventoryRepository

class AddWarehouseUseCase (private val repository: InventoryRepository) {
        suspend operator fun invoke(warehouse: WarehouseModel) {
            repository.addWarehouse(warehouse)
        }
}