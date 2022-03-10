package com.example.myapplication.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.myapplication.ui.products.Product

class ProductPreviewProvider : PreviewParameterProvider<Product> {
    override val values = sequenceOf(
        Product(
            id = 1,
            name = "Nougat",
            price = 5.0,
            availableQuantity = 15
        ),
        Product(
            id = 1,
            name = "KitKat",
            price = 3.99,
            availableQuantity = 35
        ),
        Product(
            id = 1,
            name = "Tiramisu",
            price = 7.99,
            availableQuantity = 5
        )
    )
}