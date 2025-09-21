package com.example.sklad.ui.warehouse.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sklad.domain.usecases.warehouse.AddWarehouseUseCase
import com.example.sklad.domain.usecases.warehouse.DeleteWarehouseUseCase
import com.example.sklad.domain.usecases.warehouse.GetAllWarehousesUseCase
import com.example.sklad.ui.product.list.ProductListViewModel

class WarehouseListViewModelFactory(
    private val getAllWarehousesUseCase: GetAllWarehousesUseCase,
    private val addWarehouseUseCase: AddWarehouseUseCase,
    private val deleteWarehouseUseCase: DeleteWarehouseUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WarehouseListViewModel::class.java)) {
            return WarehouseListViewModel(
                getAllWarehousesUseCase,
                addWarehouseUseCase,
                deleteWarehouseUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
    }