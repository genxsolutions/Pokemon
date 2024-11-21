package com.genxsol.pokemon.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.genxsol.pokemon.presentation.screens.PokemonDetailScreen
import com.genxsol.pokemon.presentation.viewmodel.PokemonDetailViewModel

fun NavGraphBuilder.addDetailGraph(){
    composable("${Screen.DetailsScreen.route}/{id}") { backStackEntry ->
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