package com.example.sklad.ui.product.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sklad.domain.usecases.category.GetAllCategoryUseCase
import com.example.sklad.domain.usecases.product.GetAllProductsUseCase
import com.example.sklad.domain.usecases.product.UpdateProductUseCase
import com.example.sklad.domain.usecases.warehouse.GetAllWarehousesUseCase
import com.example.sklad.ui.product.list.ProductListViewModel

class ProductDetailViewModelFactory(
    private val updateProductUseCase: UpdateProductUseCase,
    private val getAllCategory: GetAllCategoryUseCase,
    private val getAllWarehouses: GetAllWarehousesUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java)) {
            return ProductDetailViewModel(
                updateProductUseCase,
                getAllCategory,
                getAllWarehouses
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}