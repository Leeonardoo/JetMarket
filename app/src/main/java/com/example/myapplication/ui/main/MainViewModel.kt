package com.example.myapplication.ui.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        Timber.d("Initializing")
    }

    private val _switchDrawer = Channel<Unit>(Channel.BUFFERED)
    val switchDrawer = _switchDrawer.receiveAsFlow()

    fun openDrawer() {
        _switchDrawer.trySend(Unit)
    }
}