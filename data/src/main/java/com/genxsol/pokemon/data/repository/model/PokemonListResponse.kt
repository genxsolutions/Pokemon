package com.genxsol.pokemon.data.repository.model

import com.genxsol.pokemon.domain.model.Pokemon
import com.genxsol.pokemon.domain.model.Pokemons
import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
){
    fun toDomainModel() = Pokemons(
        count = count,
        next = next,
        previous = previous,
        results = results.map { it.toDomainModel() }
    )
}

data class Result(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
){
    fun toDomainModel() = Pokemon(
        id = url.split("/".toRegex()).dropLast(1).last(),
        name = name,
        url = url
    )
}