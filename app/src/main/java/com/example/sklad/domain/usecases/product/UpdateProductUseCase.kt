package com.example.sklad.domain.usecases.product

import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.repository.InventoryRepository

class UpdateProductUseCase(private val repository: InventoryRepository) {
    suspend operator fun invoke(product: ProductModel){
        repository.updateProduct(product)
    }
}