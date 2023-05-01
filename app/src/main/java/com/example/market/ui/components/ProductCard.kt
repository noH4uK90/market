package com.example.market.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.market.dto.ProductDto
import com.example.market.ui.Converter.toBitmap

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: ProductDto
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row {
            Log.wtf("image", product.image.toString())
            Log.i("space", "space")
            if (product.image.isNullOrBlank())
                Image(imageVector = Icons.Default.Warning, contentDescription = "Фотография продукта")
            else
                Image(bitmap = product.image.toBitmap().asImageBitmap(), contentDescription = "Фотография продукта")
        }
        Row {
            Column {
                Text(text = product.name.name)

                Text(text = product.description)

                Text(text = product.price.toString())

                Text(text = product.count.toString())
            }
        }
    }
}