package com.example.market.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.market.MainApplication
import com.example.market.data.ProductRepository
import com.example.market.dto.ProductDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface ProductUiState {
    data class Success(val items: List<ProductDto>): ProductUiState
    object Loading: ProductUiState
    object Error: ProductUiState
}

class ProductViewModel(private val productRepository: ProductRepository): ViewModel() {

    var productUiState: ProductUiState by mutableStateOf(ProductUiState.Loading)

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            productUiState = ProductUiState.Loading
            productUiState = try {
                ProductUiState.Success(productRepository.getProducts())
            } catch (e: IOException) {
                ProductUiState.Error
            } catch (e: HttpException) {
                ProductUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MainApplication)
                val productRepository = application.container.productRepository
                ProductViewModel(productRepository)
            }
        }
    }
}