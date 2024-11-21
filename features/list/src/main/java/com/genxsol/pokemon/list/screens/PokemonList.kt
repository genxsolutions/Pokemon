package com.genxsol.pokemon.list.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.genxsol.pokemon.list.screens.state.Pokemon

@Composable
fun PokemonList(pokemons: List<Pokemon>, onItemClick: (String) -> Unit = {}) {
    LazyColumn {
        items(pokemons.size, key = { pokemons[it].id }) {
            PokemonItem(pokemon = pokemons[it], onItemClick = onItemClick)
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon, onItemClick: (String) -> Unit = {}) {
    Card(modifier = Modifier.padding(8.dp),
        onClick = { onItemClick(pokemon.id) }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = pokemon.name, modifier = Modifier.padding(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonListPreview() {
    PokemonList(
        pokemons = listOf(
            Pokemon("1", "Bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"),
            Pokemon("2", "Bulbasaur", "https://pokeapi.co/api/v2/pokemon/2/"),
            Pokemon("3", "Bulbasaur", "https://pokeapi.co/api/v2/pokemon/3/"),
        )
    )
}