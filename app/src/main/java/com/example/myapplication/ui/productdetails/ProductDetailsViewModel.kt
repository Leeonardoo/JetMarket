package com.example.myapplication.ui.productdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.destinations.ProductDetailsDialogDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = ProductDetailsDialogDestination.argsFrom(savedStateHandle)

    val product = args.product

}