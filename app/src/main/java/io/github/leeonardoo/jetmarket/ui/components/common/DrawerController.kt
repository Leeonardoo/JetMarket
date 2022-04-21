package io.github.leeonardoo.jetmarket.ui.components.common

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api

interface DrawerController {
    suspend fun open()
    suspend fun close()
}

@OptIn(ExperimentalMaterial3Api::class)
class DrawerControllerImpl constructor(
    private val drawerState: DrawerState
) : DrawerController {

    override suspend fun open() {
        drawerState.open()
    }

    override suspend fun close() {
        drawerState.close()
    }
}