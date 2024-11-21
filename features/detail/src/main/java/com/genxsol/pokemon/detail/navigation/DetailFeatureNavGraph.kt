package com.genxsol.pokemon.detail.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.genxsol.pokemon.common.navigation.Destination
import com.genxsol.pokemon.detail.ui.PokemonDetailScreen
import com.genxsol.pokemon.detail.ui.PokemonDetailViewModel

fun NavGraphBuilder.addDetailGraph(){
    composable("${Destination.Detail.route}/{id}") { backStackEntry ->
        val id = backStackEntry.arguments?.getString("id")
            ?: throw IllegalArgumentException("Id is required")
        val viewmodel: PokemonDetailViewModel = hiltViewModel()
        PokemonDetailScreen(
            pokemonId = id,
            viewmodel.uiState.collectAsStateWithLifecycle()
        ){
            viewmodel.fetchPokemonDetail(it)
        }
    }
}