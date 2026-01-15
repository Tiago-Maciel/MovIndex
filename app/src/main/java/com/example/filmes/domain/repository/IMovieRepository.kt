package com.example.filmes.domain.repository

import androidx.paging.PagingData
import com.example.filmes.domain.model.Movie
import com.example.filmes.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getPopularMovies(): Flow<PagingData<Movie>>
    fun searchMovies(query: String): Flow<PagingData<Movie>>
    suspend fun getMovieDetails(movieId: Int): Result<MovieDetails>
    fun getAllFavorites(): Flow<List<Movie>>
    suspend fun addToFavorites(movie: Movie)
    suspend fun removeFromFavorites(movieId: Int)
    suspend fun isFavorite(movieId: Int): Boolean
}
