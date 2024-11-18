package com.genxsol.pokemon.data.repository.api

import com.genxsol.pokemon.data.repository.model.PokemonDetailResponse
import com.genxsol.pokemon.data.repository.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("pokemon")
    suspend fun fetchPokemonList(): Response<PokemonListResponse>

    @GET("pokemon/{id}")
    suspend fun fetchPokemonDetail(@Path("id") id: String): Response<PokemonDetailResponse>
}
