package com.example.filmes.ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmes.domain.model.Movie
import com.example.filmes.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: IMovieRepository
) : ViewModel() {

    val favorites: StateFlow<List<Movie>> = repository.getAllFavorites()
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun removeFavorite(movie: Movie) {
        viewModelScope.launch {
            repository.removeFromFavorites(movie.id)
        }
    }
}
