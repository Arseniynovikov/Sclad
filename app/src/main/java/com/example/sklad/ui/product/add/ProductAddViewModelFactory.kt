package com.example.sklad.ui.product.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sklad.domain.usecases.category.AddCategoryUseCase
import com.example.sklad.domain.usecases.category.GetAllCategoryUseCase
import com.example.sklad.domain.usecases.product.AddProductUseCase
import com.example.sklad.domain.usecases.product.UpdateProductUseCase
import com.example.sklad.domain.usecases.warehouse.GetAllWarehousesUseCase
import com.example.sklad.ui.product.update.ProductUpdateViewModel

class ProductAddViewModelFactory (
    private val addProductUseCase: AddProductUseCase,
    private val addCategoryUseCase: AddCategoryUseCase,
    private val getAllCategoryUseCase: GetAllCategoryUseCase,
    private val getAllWarehousesUseCase: GetAllWarehousesUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductAddViewModel::class.java)) {
            return ProductAddViewModel(
                addProductUseCase,
                addCategoryUseCase,
                getAllCategoryUseCase,
                getAllWarehousesUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}