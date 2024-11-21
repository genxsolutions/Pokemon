package com.genxsol.pokemon.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.genxsol.pokemon.presentation.screens.PokemonListScreen
import com.genxsol.pokemon.presentation.viewmodel.PokemonListViewModel

fun NavGraphBuilder.addListGraph(
    onNavigateToDetail: (String) -> Unit
) {
    composable(route = Screen.MainScreen.route) {
        val viewModel: PokemonListViewModel = hiltViewModel()
        PokemonListScreen(
            uiState = viewModel.uiState.collectAsStateWithLifecycle(),
            onItemClick = { id -> onNavigateToDetail(id) }
        )
    }
}