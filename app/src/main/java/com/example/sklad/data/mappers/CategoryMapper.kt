package com.example.sklad.data.mappers

import com.example.sklad.data.database.entities.Category
import com.example.sklad.data.database.entities.Product
import com.example.sklad.domain.models.CategoryModel
import com.example.sklad.domain.models.ProductModel

fun Category.toDomain(): CategoryModel =
    CategoryModel(id, name)

fun CategoryModel.toEntity(): Category =
    Category(id, name)