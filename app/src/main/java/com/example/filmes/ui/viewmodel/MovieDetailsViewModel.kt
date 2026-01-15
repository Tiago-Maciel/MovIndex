package com.example.filmes.ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmes.domain.model.MovieDetails
import com.example.filmes.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class MovieDetailsUiState {
    data object Loading : MovieDetailsUiState()
    data class Success(val movieDetails: MovieDetails) : MovieDetailsUiState()
    data class Error(val message: String) : MovieDetailsUiState()
}

class MovieDetailsViewModel(
    private val repository: IMovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)
    val uiState: StateFlow<MovieDetailsUiState> = _uiState.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _uiState.value = MovieDetailsUiState.Loading
            
            repository.getMovieDetails(movieId).fold(
                onSuccess = { movieDetails ->
                    _uiState.value = MovieDetailsUiState.Success(movieDetails)
                    checkFavoriteStatus(movieId)
                },
                onFailure = { error ->
                    _uiState.value = MovieDetailsUiState.Error(
                        error.message ?: "Erro ao carregar detalhes do filme"
                    )
                }
            )
        }
    }

    private fun checkFavoriteStatus(movieId: Int) {
        viewModelScope.launch {
            _isFavorite.value = repository.isFavorite(movieId)
        }
    }

    fun toggleFavorite(movie: com.example.filmes.domain.model.Movie) {
        viewModelScope.launch {
            if (_isFavorite.value) {
                repository.removeFromFavorites(movie.id)
                _isFavorite.value = false
            } else {
                repository.addToFavorites(movie)
                _isFavorite.value = true
            }
        }
    }
}
