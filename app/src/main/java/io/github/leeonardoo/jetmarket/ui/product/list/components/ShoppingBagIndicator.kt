package io.github.leeonardoo.jetmarket.ui.product.list.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.leeonardoo.jetmarket.R
import io.github.leeonardoo.jetmarket.ui.theme.JetMarketTheme

@Composable
fun ShoppingBagIndicator(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    currentPrice: String,
    onClick: () -> Unit
) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        color = MaterialTheme.colorScheme.tertiaryContainer,
        contentColor = MaterialTheme.colorScheme.onTertiaryContainer
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .padding(paddingValues),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.see_bag),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = currentPrice,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        }
    }
}

@Preview
@Composable
fun PreviewShoppingBagIndicatorDay() {
    JetMarketTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            ShoppingBagIndicator(currentPrice = "R$ 25,00") {
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewShoppingBagIndicatorNight() {
    JetMarketTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            ShoppingBagIndicator(currentPrice = "R$ 25,00") {
            }
        }
    }
}