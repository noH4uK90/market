package com.example.market.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.market.R
import com.example.market.dto.ProductDto
import com.example.market.ui.components.ProductCard

@Composable
fun ProductScreen(
    navController: NavController,
    productUiState: ProductUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when(productUiState) {
        is ProductUiState.Loading -> LoadingScreen(modifier)
        is ProductUiState.Success -> Products(modifier, productUiState.items)
        is ProductUiState.Error -> ErrorScreen(retryAction, modifier)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            imageVector = Icons.Default.Refresh,
            contentDescription = stringResource(Icons.Default.Refresh.hashCode())
        )
    }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.app_name))
        Button(onClick = retryAction) {
            Text(stringResource(androidx.compose.material3.R.string.dialog))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Products(
    modifier: Modifier,
    products: List<ProductDto>
) {

    val scrollState = rememberScrollState()

    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .verticalScroll(scrollState, true)
        ) {
            products.map { product ->
                ProductCard(product = product)
            }
        }
    }
}