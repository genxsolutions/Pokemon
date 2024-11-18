package com.genxsol.pokemon.presentation.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.genxsol.pokemon.presentation.base.UIState
import com.genxsol.pokemon.presentation.components.EmptyState
import com.genxsol.pokemon.presentation.components.ErrorState
import com.genxsol.pokemon.presentation.components.LoadingIndicator
import com.genxsol.pokemon.presentation.components.list.PokemonList
import com.genxsol.pokemon.presentation.viewmodel.PokemonListViewModel

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState.value) {
        is UIState.Loading -> LoadingIndicator()
        is UIState.Empty -> EmptyState()
        is UIState.Success -> PokemonList(pokemons = state.data.results, onItemClick = onItemClick)
        is UIState.Error -> ErrorState(message = state.message)
    }
}