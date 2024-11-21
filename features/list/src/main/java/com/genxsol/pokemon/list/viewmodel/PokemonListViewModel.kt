package com.genxsol.pokemon.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genxsol.pokemon.common.dispatcher.DispatcherProvider
import com.genxsol.pokemon.domain.model.Pokemons
import com.genxsol.pokemon.domain.repository.PokemonRepository
import com.genxsol.pokemon.common.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository,
    private val dispatcher: DispatcherProvider
) : ViewModel() {
    private val _uiState = MutableStateFlow<UIState<Pokemons>>(UIState.Empty)
    val uiState = _uiState.asStateFlow()

    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        viewModelScope.launch(dispatcher.io) {
            _uiState.emit(UIState.Loading)
            repository.getPokemonList()
                .catch { exception ->
                    _uiState.emit(UIState.Error(exception.message ?: "Unknown error"))
                }
                .collect { result ->
                    result.fold(
                        onSuccess = { pokemons ->
                            if (pokemons.results.isEmpty()) {
                                _uiState.emit(UIState.Empty)
                            } else {
                                _uiState.emit(UIState.Success(pokemons))
                            }
                        },
                        onFailure = { error ->
                            _uiState.emit(UIState.Error(error.message ?: "Unknown error"))
                        }
                    )
                }

        }
    }
}