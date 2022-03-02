package com.example.myapplication.ui.components

import androidx.compose.material.AppBarDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun InsetMediumTopAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    actions: List<ActionItemSpec> = listOf(),
    navigationIcon: @Composable (() -> Unit)? = null,
    appBarColors: TopAppBarColors = TopAppBarDefaults.mediumTopAppBarColors(),
    onClickNavigation: () -> Unit = {}
) {
    val backgroundColor = appBarColors.containerColor(
        scrollFraction = scrollBehavior?.scrollFraction ?: 0f
    ).value
    val foregroundColors = TopAppBarDefaults.mediumTopAppBarColors(
        containerColor = Color.Transparent,
        scrolledContainerColor = Color.Transparent
    )
    // Wrapping in a Surface to handle window insets
    // https://issuetracker.google.com/issues/183161866
    Surface(color = backgroundColor) {
        MediumTopAppBar(
            title = title,
            actions = {
                ActionMenu(actions, defaultIconSpace = 3)
            },
            navigationIcon = {
                navigationIcon?.let {
                    IconButton(onClick = onClickNavigation) { it() }
                }
            },
            scrollBehavior = scrollBehavior,
            colors = foregroundColors,
            modifier = modifier
                .statusBarsPadding()
                .navigationBarsPadding(bottom = false)
        )
    }
}
