package com.example.sklad.domain.usecases.product

import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.repository.InventoryRepository

class DeleteProductUseCase(private val repository: InventoryRepository)  {
    suspend operator fun invoke(product: ProductModel) {
        repository.deleteProduct(product)
    }

}