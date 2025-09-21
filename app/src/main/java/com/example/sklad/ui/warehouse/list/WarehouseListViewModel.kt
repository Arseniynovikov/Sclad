package com.example.sklad.ui.warehouse.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sklad.domain.models.WarehouseModel
import com.example.sklad.domain.usecases.warehouse.AddWarehouseUseCase
import com.example.sklad.domain.usecases.warehouse.DeleteWarehouseUseCase
import com.example.sklad.domain.usecases.warehouse.GetAllWarehousesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WarehouseListViewModel(
    private val getAllWarehousesUseCase: GetAllWarehousesUseCase,
    private val addWarehouseUseCase: AddWarehouseUseCase,
    private val deleteWarehouseUseCase: DeleteWarehouseUseCase
) : ViewModel() {

    val warehouses: StateFlow<List<WarehouseModel>?> = getAllWarehousesUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)


    fun deleteWarehouse(warehouse: WarehouseModel){
        viewModelScope.launch {
            deleteWarehouseUseCase(warehouse)
        }
    }
}