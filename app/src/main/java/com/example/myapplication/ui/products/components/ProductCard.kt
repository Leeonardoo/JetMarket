package com.example.myapplication.ui.products.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.products.model.Product
import com.example.myapplication.ui.products.data.ProductPreviewProvider
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.utils.toCurrencyFormat

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product,
    onClickDetails: () -> Unit,
    onClickAdd: () -> Unit
) {
    Card(modifier = modifier.clickable(onClick = onClickDetails)) {
        Column(
            modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(text = product.name, style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = product.price.toCurrencyFormat(),
                style = MaterialTheme.typography.bodyMedium
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.available_template, product.availableQuantity)
                )

                Button(onClick = onClickAdd) {
                    Text(stringResource(R.string.add))
                }
            }


        }
    }
}

@Preview
@Composable
fun PreviewProductCardDay(
    @PreviewParameter(ProductPreviewProvider::class) product: Product
) {
    MyApplicationTheme {
        ProductCard(
            modifier = Modifier.fillMaxWidth(),
            product = product,
            onClickDetails = {}
        ) {
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewProductCardNight(
    @PreviewParameter(ProductPreviewProvider::class) product: Product
) {
    MyApplicationTheme {
        ProductCard(
            modifier = Modifier.fillMaxWidth(),
            product = product,
            onClickDetails = {}
        ) {
        }
    }
}