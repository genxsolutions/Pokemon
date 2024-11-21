package com.genxsol.pokemon.list.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.genxsol.pokemon.common.navigation.Destination
import com.genxsol.pokemon.list.screens.PokemonListScreen
import com.genxsol.pokemon.list.viewmodel.PokemonListViewModel

fun NavGraphBuilder.addListGraph(
    onNavigateToDetail: (String) -> Unit
) {
    composable(route = Destination.List.route) {
        val viewModel: PokemonListViewModel = hiltViewModel()
        PokemonListScreen(
            uiState = viewModel.uiState.collectAsStateWithLifecycle(),
            onItemClick = { id -> onNavigateToDetail(id) }
        )
    }
}