package com.genxsol.pokemon.list.screens.state

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.genxsol.pokemon.domain.model.Pokemons

fun Pokemons.toUiState(): ListUiState {
    return ListUiState(
        count = count,
        next = next,
        previous = previous,
        results = results.map { it.toUiState() }
    )
}

fun com.genxsol.pokemon.domain.model.Pokemon.toUiState(): Pokemon {
    return Pokemon(id, name, url)
}

@Stable
data class ListUiState(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<Pokemon>
)

@Immutable
data class Pokemon(
    val id: String,
    val name: String,
    val url: String
)