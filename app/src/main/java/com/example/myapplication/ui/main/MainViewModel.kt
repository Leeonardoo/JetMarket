package com.example.myapplication.ui.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        Timber.d("Initializing")
    }

    private val _switchDrawer = MutableSharedFlow<Unit>()
    val switchDrawer: SharedFlow<Unit> = _switchDrawer

    fun openDrawer() {
        _switchDrawer.tryEmit(Unit)
    }
}