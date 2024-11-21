package com.genxsol.pokemon.common.ui

sealed class UIState<out T> {
    data object Loading : UIState<Nothing>()
    data object Empty : UIState<Nothing>()
    data class Success<out T>(val data: T) : UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()
}