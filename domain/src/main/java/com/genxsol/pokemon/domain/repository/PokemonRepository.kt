package com.genxsol.pokemon.domain.repository

import com.genxsol.pokemon.domain.model.PokemonDetail
import com.genxsol.pokemon.domain.model.Pokemons
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonList(): Flow<Result<Pokemons>>
    suspend fun getPokemonDetail(id: String): Result<PokemonDetail>
}