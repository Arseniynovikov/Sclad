package com.example.sklad.ui.product.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.usecases.category.GetAllCategoryUseCase

import com.example.sklad.domain.usecases.product.UpdateProductUseCase
import com.example.sklad.domain.usecases.warehouse.GetAllWarehousesUseCase
import kotlinx.coroutines.launch

class ProductDetailViewModel (
    private val updateProduct: UpdateProductUseCase,
    private val getAllCategory: GetAllCategoryUseCase,
    private val getAllWarehouses: GetAllWarehousesUseCase
) : ViewModel() {

    val categoryLiveData = getAllCategory().asLiveData()
    val warehouseLiveData = getAllWarehouses().asLiveData()

    fun incQuantity(product: ProductModel){
        viewModelScope.launch {
            updateProduct(product)
        }
    }

    fun decQuantity(product: ProductModel){
        viewModelScope.launch {
            updateProduct(product)
        }
    }

}