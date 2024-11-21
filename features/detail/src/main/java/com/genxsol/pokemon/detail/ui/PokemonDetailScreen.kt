package com.genxsol.pokemon.detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import com.genxsol.pokemon.domain.model.PokemonDetail
import com.genxsol.pokemon.common.ui.UIState
import com.genxsol.pokemon.common.ui.ErrorState
import com.genxsol.pokemon.common.ui.LoadingIndicator

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
        is UIState.Success -> com.genxsol.pokemon.detail.ui.PokemonDetailComponent(pokemon = state.data)
        is UIState.Error -> ErrorState("Error: ${state.message}")
        UIState.Empty -> {}
    }
}
