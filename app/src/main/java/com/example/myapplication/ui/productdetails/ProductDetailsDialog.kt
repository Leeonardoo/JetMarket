package com.example.myapplication.ui.productdetails

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
import com.example.myapplication.R
import com.example.myapplication.ui.components.InsetSmallTopAppBar
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.utils.surfaceColorAtElevation
import com.google.accompanist.insets.ProvideWindowInsets
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
@OptIn(ExperimentalMaterial3Api::class)
fun ProductDetailsDialog() {
    //6.dp
    val scrollBehavior = remember {
        TopAppBarDefaults.pinnedScrollBehavior()
    }

    MyApplicationTheme {
        ProvideWindowInsets {
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
                            scrolledContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                16.dp
                            )
                        )
                    )
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
                        ProductDetailsDialogContent()
                    }
                })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsDialogContent() {
    val scrollState = rememberScrollState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
            .verticalScroll(scrollState)
    ) {
        val (title, price, quantity, bottomBar) = createRefs()

        Text(
            text = "KitKat",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(title) {
                    top.linkTo(parent.top, margin = 8.dp)
                })

        Text(
            text = "R$ 5,00",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(price) {
                    top.linkTo(title.bottom, margin = 8.dp)
                })

        Text(
            text = "15 disponíveis",
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
                        bias = 1f,
                        bottomMargin = 16.dp
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
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
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
    MyApplicationTheme {
        ProductDetailsDialog()
    }
}