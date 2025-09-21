package com.example.sklad.ui.product.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.usecases.category.AddCategoryUseCase
import com.example.sklad.domain.usecases.product.AddProductUseCase
import com.example.sklad.domain.usecases.product.DeleteProductUseCase
import com.example.sklad.domain.usecases.product.GetAllProductsUseCase
import com.example.sklad.domain.usecases.warehouse.AddWarehouseUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class ProductListViewModel(
    private val getAllProducts: GetAllProductsUseCase,
    private val addProduct: AddProductUseCase,
    private val deleteProduct: DeleteProductUseCase
) : ViewModel() {

    val products: StateFlow<List<ProductModel>?> = getAllProducts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun add(product: ProductModel) {
        viewModelScope.launch {
            addProduct(product)
        }
    }

    fun delete(product: ProductModel){
        viewModelScope.launch {
            deleteProduct(product)
        }
    }

}