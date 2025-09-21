package com.example.sklad.domain.usecases.category

import com.example.sklad.domain.models.CategoryModel

import com.example.sklad.domain.repository.InventoryRepository

class AddCategoryUseCase(private val repository: InventoryRepository) {
    suspend operator fun invoke(category: CategoryModel) {
        repository.addCategory(category)
    }
}