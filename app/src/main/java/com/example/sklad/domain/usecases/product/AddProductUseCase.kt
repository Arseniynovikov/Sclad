package com.example.sklad.domain.usecases.product

import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.repository.InventoryRepository

class AddProductUseCase ( private val repository: InventoryRepository) {
    suspend operator fun invoke(product: ProductModel) {
        if (product.quantity < 0) throw IllegalArgumentException("Quantity must be ≥ 0")
        repository.addProduct(product)
    }
}