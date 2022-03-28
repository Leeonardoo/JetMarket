package com.example.myapplication.ui.products.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Long,
    val name: String,
    val price: Double,
    val availableQuantity: Int
) : Parcelable