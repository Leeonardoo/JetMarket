package com.example.myapplication.ui.users

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.components.InsetLargeTopAppBar
import com.example.myapplication.ui.components.common.DrawerController
import com.example.myapplication.ui.users.components.UserRow
import com.example.myapplication.ui.users.data.UserPreviewProvider
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.Scaffold
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun UsersScreen(navigator: DestinationsNavigator, drawerController: DrawerController) {
    val coroutineScope = rememberCoroutineScope()

    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            InsetLargeTopAppBar(
                title = { Text(stringResource(R.string.users_label)) },
                navigationIcon = { Icon(Icons.Filled.Menu, null) },
                scrollBehavior = scrollBehavior
            ) {
                coroutineScope.launch { drawerController.open() }
            }
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val users = UserPreviewProvider().values.toMutableList()
                repeat(74) {
                    users.add(users.random())
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = rememberInsetsPaddingValues(
                        insets = LocalWindowInsets.current.navigationBars,
                        additionalTop = 16.dp,
                        additionalBottom = 16.dp + 56.dp //FAB height
                    )
                ) {
                    items(users) { user ->
                        UserRow(
                            user = user,
                            onClick = {}
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.navigationBarsWithImePadding(),
                onClick = { /*TODO*/ }) {
                Icon(Icons.Outlined.Add, null)
            }
        }
    )
}