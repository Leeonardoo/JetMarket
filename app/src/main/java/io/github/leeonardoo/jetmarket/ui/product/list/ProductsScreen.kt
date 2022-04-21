package io.github.leeonardoo.jetmarket.ui.product.list

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.Scaffold
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.github.leeonardoo.jetmarket.R
import io.github.leeonardoo.jetmarket.ui.components.InsetLargeTopAppBar
import io.github.leeonardoo.jetmarket.ui.components.common.DrawerController
import io.github.leeonardoo.jetmarket.ui.destinations.ProductDetailsDialogDestination
import io.github.leeonardoo.jetmarket.ui.product.list.components.ProductCard
import io.github.leeonardoo.jetmarket.ui.product.list.components.ShoppingBagIndicator
import io.github.leeonardoo.jetmarket.ui.product.list.data.ProductPreviewProvider
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination(start = true)
fun ProductsScreen(navigator: DestinationsNavigator, drawerController: DrawerController) {
    val coroutineScope = rememberCoroutineScope()

    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            InsetLargeTopAppBar(
                title = { Text(stringResource(R.string.products_label)) },
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
                val products = ProductPreviewProvider().values.toMutableList()
                repeat(74) {
                    products.add(products.random())
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = rememberInsetsPaddingValues(
                        insets = LocalWindowInsets.current.navigationBars,
                        additionalTop = 16.dp,
                        additionalStart = 16.dp,
                        additionalEnd = 16.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(products) { product ->
                        ProductCard(
                            product = product,
                            onClickDetails = {
                                navigator.navigate(ProductDetailsDialogDestination(product))
                            },
                            onClickAdd = {}
                        )
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