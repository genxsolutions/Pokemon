package com.genxsol.pokemon.data.repository.model

import com.genxsol.pokemon.domain.model.PokemonDetail
import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("sprites")
    val sprites: Sprite
){
    fun toDomainModel(): PokemonDetail {
        return PokemonDetail(
            id = this.id,
            name = this.name,
            height = this.height,
            imageUrl = this.sprites.frontDefault
        )
    }
}

data class Sprite(
    @SerializedName("front_default")
    val frontDefault: String
)