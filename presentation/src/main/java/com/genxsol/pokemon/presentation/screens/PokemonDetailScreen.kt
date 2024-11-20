package com.genxsol.pokemon.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import com.genxsol.pokemon.domain.model.PokemonDetail
import com.genxsol.pokemon.presentation.base.UIState
import com.genxsol.pokemon.presentation.components.ErrorState
import com.genxsol.pokemon.presentation.components.LoadingIndicator
import com.genxsol.pokemon.presentation.components.detail.PokemonDetailComponent

@Composable
fun PokemonDetailScreen(
    pokemonId: String,
    state: State<UIState<PokemonDetail>>,
    fetchPokemonDetail: (String) -> Unit = {}
) {

    // Trigger API call only once per ID
    LaunchedEffect(pokemonId) {
        fetchPokemonDetail(pokemonId)
    }

    when (val state = state.value) {
        is UIState.Loading -> LoadingIndicator()
        is UIState.Success -> PokemonDetailComponent(pokemon = state.data)
        is UIState.Error -> ErrorState("Error: ${state.message}")
        UIState.Empty -> {}
    }
}
