package com.example.sklad.ui.product.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.usecases.category.GetAllCategoryUseCase
import com.example.sklad.domain.usecases.product.UpdateProductUseCase
import com.example.sklad.domain.usecases.warehouse.GetAllWarehousesUseCase
import kotlinx.coroutines.launch

class ProductUpdateViewModel (
    private val updateProduct: UpdateProductUseCase,
    private val getAllCategory: GetAllCategoryUseCase,
    private val getAllWarehouses: GetAllWarehousesUseCase
) : ViewModel() {

    val categoriesLiveData = getAllCategory().asLiveData()
    val warehousesLiveData = getAllWarehouses().asLiveData()

    fun saveUpdate(product:ProductModel){
        viewModelScope.launch {
            updateProduct(product)
        }
    }

}