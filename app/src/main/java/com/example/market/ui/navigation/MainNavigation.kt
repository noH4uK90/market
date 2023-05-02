package com.example.market.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.market.ui.screens.AuthorizationViewModel
import com.example.market.ui.screens.AuthorizeScreen
import com.example.market.ui.screens.ProductScreen
import com.example.market.ui.screens.ProductViewModel
import com.example.market.ui.screens.RegistrationScreen
import com.example.market.ui.screens.RegistrationViewModel

@Composable
fun MainNavigation(
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
    authorizationViewModel: AuthorizationViewModel = viewModel(factory = AuthorizationViewModel.Factory),
    productViewModel: ProductViewModel = viewModel(factory = ProductViewModel.Factory),
    registrationViewModel: RegistrationViewModel = viewModel(factory = RegistrationViewModel.Factory),
    startDestination: String = "AuthorizationScreen"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("AuthorizationScreen") {
            AuthorizeScreen(navController = navController, viewModel = authorizationViewModel)
        }
        composable("ProductScreen") {
            ProductScreen(navController = navController, productUiState = productViewModel.productUiState, retryAction = productViewModel::getProducts)
        }
        composable("RegistrationScreen") {
            RegistrationScreen(navController = navController, viewModel = registrationViewModel)
        }
    }
}