package com.example.myapplication.ui.products

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.components.InsetLargeTopAppBar
import com.example.myapplication.ui.products.components.ShoppingBagIndicator
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen() {

    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
    }
    val scrollState = rememberScrollState()

    ProvideWindowInsets {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                InsetLargeTopAppBar(
                    title = { Text("Title") },
                    scrollBehavior = scrollBehavior
                )
            },
            content = { paddingValues ->
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                    ) {
                        repeat(74) {
                            Greeting("Android")
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

                }
            })
    }
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