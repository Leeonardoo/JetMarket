package io.github.leeonardoo.jetmarket.ui.product.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.leeonardoo.jetmarket.ui.destinations.ProductDetailsDialogDestination
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = ProductDetailsDialogDestination.argsFrom(savedStateHandle)

    val product = args.product

}