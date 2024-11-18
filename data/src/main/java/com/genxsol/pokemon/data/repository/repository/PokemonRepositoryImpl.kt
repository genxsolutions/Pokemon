package com.genxsol.pokemon.data.repository.repository

import com.genxsol.pokemon.data.repository.api.PokemonApi
import com.genxsol.pokemon.domain.model.PokemonDetail
import com.genxsol.pokemon.domain.model.Pokemons
import com.genxsol.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi
) : PokemonRepository {

    override suspend fun getPokemonList(): Flow<Result<Pokemons>> = flow {
        try {
            val response = api.fetchPokemonList()
            if (response.isSuccessful) {
                val pokemonList = response.body()?.toDomainModel() ?: throw Exception("Error: Empty response")
                emit(Result.success(pokemonList))
            } else {
                emit(Result.failure(Exception("Error: ${response.message()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun getPokemonDetail(id: String): Result<PokemonDetail> {
        return try {
            val response = api.fetchPokemonDetail(id)
            if (response.isSuccessful) {
                val pokemonDetail = response.body()?.toDomainModel() ?: throw Exception("Error: Empty response")
                Result.success(pokemonDetail)
            } else {
                Result.failure(Exception("Error: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
