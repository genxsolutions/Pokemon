package com.genxsol.pokemon.list.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.genxsol.pokemon.domain.model.Pokemon
import com.genxsol.pokemon.domain.model.Pokemons
import com.genxsol.pokemon.common.ui.UIState
import com.genxsol.pokemon.common.ui.EmptyState
import com.genxsol.pokemon.common.ui.ErrorState
import com.genxsol.pokemon.common.ui.LoadingIndicator
import com.genxsol.pokemon.list.screens.state.toUiState

@Composable
internal fun PokemonListScreen(uiState: State<UIState<Pokemons>>, onItemClick: (String) -> Unit) {
    when (val state = uiState.value) {
        is UIState.Loading -> LoadingIndicator()
        is UIState.Empty -> EmptyState()
        is UIState.Success -> PokemonList(pokemons = state.data.toUiState().results, onItemClick = onItemClick)
        is UIState.Error -> ErrorState(message = state.message)
    }
}

@Composable
@Preview(showBackground = true)
fun LoadingStatePreview() {
    PokemonListScreen(
        uiState = remember { mutableStateOf(UIState.Loading) },
        onItemClick = {}
    )
}

@Composable
@Preview(showBackground = true)
fun EmptyStatePreview() {
    PokemonListScreen(
        uiState = remember { mutableStateOf(UIState.Empty) },
        onItemClick = {}
    )
}

@Composable
@Preview(showBackground = true)
fun SuccessStatePreview() {
    val successState = UIState.Success(
        data = Pokemons(
            count = 3,
            next = "next_url",
            previous = null,
            results = listOf(
                Pokemon("1", "Bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"),
                Pokemon("2", "Ivysaur", "https://pokeapi.co/api/v2/pokemon/2/"),
                Pokemon("3", "Venusaur", "https://pokeapi.co/api/v2/pokemon/3/")
            )
        )
    )
    PokemonListScreen(
        uiState = remember { mutableStateOf(successState) },
        onItemClick = {}
    )
}

@Composable
@Preview(showBackground = true)
fun ErrorStatePreview() {
    PokemonListScreen(
        uiState = remember { mutableStateOf(UIState.Error("Something went wrong!")) },
        onItemClick = {}
    )
}