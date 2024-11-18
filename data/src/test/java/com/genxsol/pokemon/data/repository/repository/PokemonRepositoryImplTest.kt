package com.genxsol.pokemon.data.repository.repository

import com.genxsol.pokemon.data.repository.api.PokemonApi
import com.genxsol.pokemon.data.repository.model.PokemonListResponse
import com.genxsol.pokemon.data.repository.model.Result
import com.genxsol.pokemon.domain.model.Pokemon
import com.genxsol.pokemon.domain.model.Pokemons
import com.genxsol.pokemon.domain.repository.PokemonRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class PokemonRepositoryImplTest {

    private val api: PokemonApi = mockk()

    private lateinit var repository: PokemonRepository

    @Before
    fun setup() {
        repository = PokemonRepositoryImpl(api)
    }

    @Test
    fun `test getPokemonList success`() {
        runBlocking {
            val pokemonListResponse = PokemonListResponse(
                count = 10, next = "", previous = "", results = listOf(
                    Result("name1", "https://pokeapi.co/api/v2/pokemon/1/"),
                    Result("name2", "https://pokeapi.co/api/v2/pokemon/2/")
                )
            )
            val response = Response.success(pokemonListResponse)

            coEvery { api.fetchPokemonList() } returns (response)

            val expectedResult = Pokemons(
                count = 10,
                next = "",
                previous = "",
                results = listOf(
                    Pokemon(id = "1", name = "name1", url = "https://pokeapi.co/api/v2/pokemon/1/"),
                    Pokemon(id = "2", name = "name2", url = "https://pokeapi.co/api/v2/pokemon/2/")
                )
            )
            repository.getPokemonList().collect {
                assert(it.isSuccess)
                assertEquals(it.getOrNull(), expectedResult)
            }
        }
    }
}