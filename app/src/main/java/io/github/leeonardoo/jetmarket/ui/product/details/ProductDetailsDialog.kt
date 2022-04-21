package io.github.leeonardoo.jetmarket.ui.product.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.ui.Scaffold
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import io.github.leeonardoo.jetmarket.R
import io.github.leeonardoo.jetmarket.ui.components.InsetSmallTopAppBar
import io.github.leeonardoo.jetmarket.ui.product.list.model.Product
import io.github.leeonardoo.jetmarket.ui.theme.JetMarketTheme
import io.github.leeonardoo.jetmarket.utils.surfaceColorAtElevation
import io.github.leeonardoo.jetmarket.utils.toCurrencyFormat

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@Destination(
    navArgsDelegate = ProductDetailsNavArgs::class
)
fun ProductDetailsDialog(
    navigator: DestinationsNavigator,
    viewModel: ProductDetailsViewModel = viewModel()
) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            InsetSmallTopAppBar(
                title = { },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                },
                appBarColors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(6.dp),
                    scrolledContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(16.dp)
                )
            ) {
                navigator.navigateUp()
            }
        },
        content = { paddingValues ->
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp
            ) {
                ProductDetailsDialogContent(viewModel.product)
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsDialogContent(product: Product) {
    val scrollState = rememberScrollState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
            .navigationBarsPadding()
            .verticalScroll(scrollState)
    ) {
        val (title, price, quantity, bottomBar) = createRefs()

        Text(
            text = product.name,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(title) {
                    top.linkTo(parent.top)
                })

        Text(
            text = product.price.toCurrencyFormat(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(price) {
                    top.linkTo(title.bottom, margin = 8.dp)
                })

        Text(
            text = stringResource(R.string.available_template, product.availableQuantity),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(quantity) {
                    top.linkTo(price.bottom, margin = 16.dp)
                })

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .constrainAs(bottomBar) {
                    linkTo(
                        top = quantity.bottom,
                        bottom = parent.bottom,
                        bias = 1f
                    )
                },
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedCard(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth(),
                shape = CircleShape
            ) {

                var count by remember { mutableStateOf(1) }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.wrapContentWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { count-- }) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    Text(
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.requiredWidth(24.dp),
                        text = count.toString(),
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary
                    )

                    IconButton(onClick = { count++ }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(text = stringResource(R.string.add_to_bag))
            }
        }
    }
}

@Preview
@Composable
fun PreviewProductDetailsDialog() {
    JetMarketTheme {
        ProductDetailsDialog(EmptyDestinationsNavigator)
    }
}