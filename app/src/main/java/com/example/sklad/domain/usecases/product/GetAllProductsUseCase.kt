package com.example.sklad.domain.usecases.product

import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.repository.InventoryRepository
import kotlinx.coroutines.flow.Flow

class GetAllProductsUseCase(private val repository: InventoryRepository) {
    operator fun invoke(): Flow<List<ProductModel>> = repository.getAllProducts()
}