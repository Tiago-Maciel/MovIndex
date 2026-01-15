package com.example.filmes.ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.filmes.domain.model.Movie
import com.example.filmes.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

data class MovieListUiState(
    val searchQuery: String = "",
    val isSearching: Boolean = false,
    val error: String? = null
)

class MovieListViewModel(
    private val repository: IMovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieListUiState())
    val uiState: StateFlow<MovieListUiState> = _uiState.asStateFlow()

    private val _movies = MutableStateFlow<kotlinx.coroutines.flow.Flow<PagingData<Movie>>>(
        repository.getPopularMovies().cachedIn(viewModelScope)
    )
    val movies: StateFlow<kotlinx.coroutines.flow.Flow<PagingData<Movie>>> = _movies.asStateFlow()

    fun searchMovies(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
        
        if (query.isEmpty()) {
            _movies.value = repository.getPopularMovies().cachedIn(viewModelScope)
            _uiState.value = _uiState.value.copy(isSearching = false)
        } else {
            _movies.value = repository.searchMovies(query).cachedIn(viewModelScope)
            _uiState.value = _uiState.value.copy(isSearching = true)
        }
    }

    fun clearSearch() {
        _uiState.value = _uiState.value.copy(searchQuery = "", isSearching = false)
        _movies.value = repository.getPopularMovies().cachedIn(viewModelScope)
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
