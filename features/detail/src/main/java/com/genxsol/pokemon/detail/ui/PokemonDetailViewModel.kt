package com.genxsol.pokemon.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genxsol.pokemon.common.dispatcher.DispatcherProvider
import com.genxsol.pokemon.domain.model.PokemonDetail
import com.genxsol.pokemon.domain.repository.PokemonRepository
import com.genxsol.pokemon.common.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<PokemonDetail>>(UIState.Empty)
    val uiState: StateFlow<UIState<PokemonDetail>> = _uiState.asStateFlow()

    fun fetchPokemonDetail(id: String) {
        viewModelScope.launch(dispatcher.io) {
            _uiState.value = UIState.Loading
            try {
                val result = repository.getPokemonDetail(id)
                result.fold(
                    onSuccess = { _uiState.emit(UIState.Success(it)) },
                    onFailure = { _uiState.emit(UIState.Error(it.message ?: "Unknown error")) }
                )
            } catch (e: Exception) {
                _uiState.value = UIState.Error(e.message ?: "Unknown error")
            }
        }
    }
}