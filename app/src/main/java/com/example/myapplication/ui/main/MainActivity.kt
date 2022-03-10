package com.example.myapplication.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.core.view.WindowCompat
import com.example.myapplication.ui.components.InsetLargeTopAppBar
import com.example.myapplication.ui.products.components.ShoppingBagIndicator
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val decayAnimationSpec = rememberSplineBasedDecay<Float>()
            val scrollBehavior = remember(decayAnimationSpec) {
                TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
            }

            MyApplicationTheme {
                ProvideWindowInsets {
                    ModalNavigationDrawer(drawerContent = ) {
                        
                    }
                }
                

                ProvideWindowInsets {
                    Scaffold(
                        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

                        content = { paddingValues ->
                            // A surface container using the 'background' color from the theme
                            Surface(
                                modifier = Modifier
                                    .padding(paddingValues)
                                    .fillMaxSize(),
                                color = MaterialTheme.colorScheme.background
                            ) {

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
        }
    }
}