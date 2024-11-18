package com.genxsol.pokemon.presentation.navigation

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main")
    data object DetailsScreen : Screen("detail")
}