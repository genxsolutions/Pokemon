package com.genxsol.pokemon.presentation.components.list

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.genxsol.pokemon.presentation.screens.state.Pokemon
import org.junit.Rule
import org.junit.Test

class PokemonListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPokemonList_DisplayedCorrectly() {
        // Arrange
        val pokemonList = listOf(
            Pokemon("1", "Bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"),
            Pokemon("2", "Ivysaur", "https://pokeapi.co/api/v2/pokemon/2/"),
            Pokemon("3", "Venusaur", "https://pokeapi.co/api/v2/pokemon/3/")
        )

        // Act
        composeTestRule.setContent {
            PokemonList(pokemons = pokemonList)
        }

        // Assert
        pokemonList.forEach { pokemon ->
            composeTestRule.onNodeWithText(pokemon.name).assertIsDisplayed()
        }
    }

    @Test
    fun testPokemonItem_ClickAction() {
        // Arrange
        val pokemonList = listOf(
            Pokemon("1", "Bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"),
            Pokemon("2", "Ivysaur", "https://pokeapi.co/api/v2/pokemon/2/"),
            Pokemon("3", "Venusaur", "https://pokeapi.co/api/v2/pokemon/3/")
        )
        var clickedPokemonId: String? = null

        // Act
        composeTestRule.setContent {
            PokemonList(
                pokemons = pokemonList,
                onItemClick = { id -> clickedPokemonId = id }
            )
        }

        // Perform a click on the first Pokemon
        composeTestRule.onNodeWithText("Bulbasaur").performClick()

        // Assert
        assert(clickedPokemonId == "1") { "Expected clicked Pokemon ID to be '1', but was '$clickedPokemonId'" }
    }
}