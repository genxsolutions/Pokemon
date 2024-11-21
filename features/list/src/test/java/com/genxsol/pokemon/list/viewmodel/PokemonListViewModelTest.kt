package com.genxsol.pokemon.list.viewmodel

import app.cash.turbine.test
import com.genxsol.pokemon.common.dispatcher.DispatcherProvider
import com.genxsol.pokemon.domain.model.Pokemon
import com.genxsol.pokemon.domain.model.Pokemons
import com.genxsol.pokemon.domain.repository.PokemonRepository
import com.genxsol.pokemon.list.TestDispatcherProvider
import com.genxsol.pokemon.common.ui.UIState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class PokemonListViewModelTest {

    private lateinit var viewModel: PokemonListViewModel
    private lateinit var dispatcher: DispatcherProvider
    private val repository: PokemonRepository = mockk()

    @Before
    fun setup() {
        dispatcher = TestDispatcherProvider()
    }

    @Test
    fun `fetchPokemonList emits Success when data is fetched successfully`() = runTest {
        // Mock successful response
        val mockPokemons =  Pokemons(
            count = 1,
            next = "",
            previous = null,
            results = listOf(Pokemon(id = "123", name = "pokemon1", url = "url1"))
        )

        coEvery { repository.getPokemonList() } returns flow {
            emit(Result.success(mockPokemons))
        }

        viewModel = PokemonListViewModel(repository, dispatcher)

        viewModel.uiState.test {
            assertEquals(UIState.Success(mockPokemons), awaitItem()) // Success state
            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) { repository.getPokemonList() }
    }

    @Test
    fun `fetchPokemonList emits Empty when data is fetched successfully but no result`() = runTest {
        // Mock successful response
        val mockPokemons =  Pokemons(
            count = 1,
            next = "",
            previous = null,
            results = emptyList()
        )

        coEvery { repository.getPokemonList() } returns flow {
            emit(Result.success(mockPokemons))
        }

        viewModel = PokemonListViewModel(repository, dispatcher)

        viewModel.uiState.test {
            assertEquals(UIState.Empty, awaitItem()) // Success state
            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) { repository.getPokemonList() }
    }

}