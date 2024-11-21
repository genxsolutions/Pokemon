package com.genxsol.pokemon.common.navigation

sealed class Destination(val route: String) {
    data object List : Destination("list")
    data object Detail : Destination("detail")
}