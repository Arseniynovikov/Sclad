package com.example.sklad.data.mappers

import com.example.sklad.data.database.entities.Product
import com.example.sklad.data.database.entities.Warehouse
import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.models.WarehouseModel

fun Warehouse.toDomain(): WarehouseModel =
    WarehouseModel(id, name, location, description)

fun WarehouseModel.toEntity(): Warehouse =
    Warehouse(id, name, location, description)