package com.example.sklad.data.mappers

import com.example.sklad.data.database.entities.Product
import com.example.sklad.domain.models.ProductModel


fun Product.toDomain(): ProductModel =
    ProductModel(id, name, barcode, description, quantity, unit, categoryId, warehouseId)

fun ProductModel.toEntity(): Product =
    Product(id, name, barcode, description, quantity, unit, categoryId, warehouseId)
