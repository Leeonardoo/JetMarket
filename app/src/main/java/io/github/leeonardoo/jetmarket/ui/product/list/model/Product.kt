package io.github.leeonardoo.jetmarket.ui.product.list.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Long,
    val name: String,
    val price: Double,
    val availableQuantity: Int
) : Parcelable