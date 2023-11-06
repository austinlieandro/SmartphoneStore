package com.example.ecommerceapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ecommerceapp.R
import com.example.ecommerceapp.ui.theme.EcommerceAppTheme
import com.example.ecommerceapp.ui.theme.Shapes

@Composable
fun CartItem(
    handphoneId: Long,
    image: String,
    title: String,
    totalPrice: Int,
    count: Int,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = image,
            contentDescription = stringResource(R.string.cart_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(Shapes.small)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = stringResource(
                    R.string.price,
                    totalPrice
                ),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall,
            )
        }
        ProductCounter(
            orderId = handphoneId,
            orderCount = count,
            onProductIncreased = { onProductCountChanged(handphoneId, count + 1) },
            onProductDecreased = { onProductCountChanged(handphoneId, count - 1) },
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CartItemPreview() {
    EcommerceAppTheme {
        CartItem(
            4, "https://m.media-amazon.com/images/I/61-r+Yodx9L.jpg", "Jaket Hoodie Dicoding", 4000, 0,
            onProductCountChanged = { rewardId, count -> },
        )
    }
}