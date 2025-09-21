package com.example.sklad.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WarehouseModel (
    val id: Int,
    val name: String,
    val location: String?,
    val description: String?
): Parcelable