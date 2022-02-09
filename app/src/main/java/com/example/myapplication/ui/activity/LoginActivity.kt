package com.example.myapplication.ui.activity

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
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.myapplication.ui.components.InsetLargeTopAppBar
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.accompanist.insets.ProvideWindowInsets

@OptIn(ExperimentalMaterial3Api::class)
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val decayAnimationSpec = rememberSplineBasedDecay<Float>()
            val scrollBehavior = remember(decayAnimationSpec) {
                TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
            }
            val scrollState = rememberScrollState()

            MyApplicationTheme {
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
                                color = MaterialTheme.colorScheme.surface,
                                tonalElevation = 1.dp
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(paddingValues)
                                        .fillMaxSize()
                                        .verticalScroll(scrollState)
                                ) {
                                    repeat(74) {
                                        Greeting2("Android")
                                    }
                                }
                            }
                        })
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MyApplicationTheme {
        Greeting2("Android")
    }
}