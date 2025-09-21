package com.example.sklad.ui.product.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sklad.domain.usecases.category.GetAllCategoryUseCase
import com.example.sklad.domain.usecases.product.AddProductUseCase
import com.example.sklad.domain.usecases.product.DeleteProductUseCase
import com.example.sklad.domain.usecases.product.GetAllProductsUseCase
import com.example.sklad.domain.usecases.product.UpdateProductUseCase
import com.example.sklad.domain.usecases.warehouse.GetAllWarehousesUseCase
import com.example.sklad.ui.product.list.ProductListViewModel

class ProductUpdateViewModelFactory(
    private val updateProductUseCase: UpdateProductUseCase,
    private val getAllCategoryUseCase: GetAllCategoryUseCase,
    private val getAllWarehousesUseCase: GetAllWarehousesUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductUpdateViewModel::class.java)) {
            return ProductUpdateViewModel(
                updateProductUseCase,
                getAllCategoryUseCase,
                getAllWarehousesUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}