package com.example.myapplication.ui.products

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.components.InsetLargeTopAppBar
import com.example.myapplication.ui.components.common.DrawerController
import com.example.myapplication.ui.destinations.ProductDetailsDialogDestination
import com.example.myapplication.ui.products.components.ShoppingBagIndicator
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
@Destination(start = true)
fun ProductsScreen(navigator: DestinationsNavigator, drawerController: DrawerController) {
    val coroutineScope = rememberCoroutineScope()

    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
    }
    val scrollState = rememberScrollState()

    com.google.accompanist.insets.ui.Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            InsetLargeTopAppBar(
                title = { Text("Title") },
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    repeat(74) {
                        Greeting("Android $it")
                    }
                }
            }
        },
        bottomBar = {
            ShoppingBagIndicator(
                paddingValues = rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.navigationBars
                ),
                currentPrice = "R$ 25,00"
            ) {
                navigator.navigate(ProductDetailsDialogDestination)
            }
        })
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}