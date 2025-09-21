package com.example.sklad.ui.product.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sklad.data.database.entities.Product
import com.example.sklad.domain.models.CategoryModel
import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.usecases.category.AddCategoryUseCase
import com.example.sklad.domain.usecases.category.GetAllCategoryUseCase
import com.example.sklad.domain.usecases.product.AddProductUseCase
import com.example.sklad.domain.usecases.product.UpdateProductUseCase
import com.example.sklad.domain.usecases.warehouse.GetAllWarehousesUseCase
import kotlinx.coroutines.launch

class ProductAddViewModel(
    private val addProductUseCase: AddProductUseCase,
    private val addCategoryUseCase: AddCategoryUseCase,
    private val getAllCategory: GetAllCategoryUseCase,
    private val getAllWarehouses: GetAllWarehousesUseCase
) : ViewModel() {

    val warehouseLiveData = getAllWarehouses().asLiveData()
    val categoryLiveData = getAllCategory().asLiveData()

    fun addCategory(category: CategoryModel){
        viewModelScope.launch {
            addCategoryUseCase(category)
        }
    }

    fun addProduct(product: ProductModel){
        viewModelScope.launch {
            addProductUseCase(product)
        }
    }

}