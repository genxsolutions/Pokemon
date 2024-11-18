package com.genxsol.pokemon.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.genxsol.pokemon.presentation.base.UIState
import com.genxsol.pokemon.presentation.components.ErrorState
import com.genxsol.pokemon.presentation.components.LoadingIndicator
import com.genxsol.pokemon.presentation.components.detail.PokemonDetailComponent
import com.genxsol.pokemon.presentation.viewmodel.PokemonDetailViewModel

@Composable
fun PokemonDetailScreen(
    pokemonId: String,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    // Trigger API call only once per ID
    LaunchedEffect(pokemonId) {
        viewModel.fetchPokemonDetail(pokemonId)
    }

    when (val state = uiState.value) {
        is UIState.Loading ->  LoadingIndicator()
        is UIState.Success -> PokemonDetailComponent(pokemon = state.data)
        is UIState.Error -> ErrorState("Error: ${state.message}")
        UIState.Empty -> {}
    }
}
