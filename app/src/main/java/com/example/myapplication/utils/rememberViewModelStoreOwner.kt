package com.example.myapplication.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner

@Composable
fun rememberViewModelStoreOwner(): ViewModelStoreOwner {
    val context = LocalContext.current
    return remember(context) { context as ViewModelStoreOwner }
}