package com.example.ecommerceapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun HandphoneItem(
    image: String,
    title: String,
    price: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
    ) {
        AsyncImage(
            model = image,
            contentDescription = stringResource(R.string.handphone_item_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(Shapes.medium)
        )
        Column {
            Text(
                text = title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = stringResource(R.string.price, price),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RewardItemPreview() {
    EcommerceAppTheme {
        HandphoneItem("https://m.media-amazon.com/images/I/61-r+Yodx9L.jpg", "Jaket Hoodie Dicoding", 4000)
    }
}