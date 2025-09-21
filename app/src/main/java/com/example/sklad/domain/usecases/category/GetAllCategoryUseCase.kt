package com.example.sklad.domain.usecases.category

import com.example.sklad.domain.models.CategoryModel
import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.repository.InventoryRepository
import kotlinx.coroutines.flow.Flow

class GetAllCategoryUseCase (private val repository: InventoryRepository) {
    operator fun invoke(): Flow<List<CategoryModel>> = repository.getAllCategory()
}