package com.example.sklad.ui.product.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sklad.domain.usecases.category.AddCategoryUseCase
import com.example.sklad.domain.usecases.product.AddProductUseCase
import com.example.sklad.domain.usecases.product.DeleteProductUseCase
import com.example.sklad.domain.usecases.product.GetAllProductsUseCase
import com.example.sklad.domain.usecases.warehouse.AddWarehouseUseCase

class ProductListViewModelFactory(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val addProductUseCase: AddProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductListViewModel::class.java)) {
            return ProductListViewModel(
                getAllProductsUseCase,
                addProductUseCase,
                deleteProductUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}