package com.example.sklad.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductModel(
    val id: Int,
    var name: String,
    var barcode: String,
    var description: String?,
    var quantity: Int,
    var unit: String,
    var categoryId: Int,
    var warehouseId: Int
): Parcelable
