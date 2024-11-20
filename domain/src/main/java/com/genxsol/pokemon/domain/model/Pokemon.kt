package com.genxsol.pokemon.domain.model

data class Pokemons(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<Pokemon>
)

data class Pokemon(
    val id: String,
    val name: String,
    val url: String
)