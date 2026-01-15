package com.example.filmes.data.datasource.remote

import com.example.filmes.BuildConfig
import com.example.filmes.data.remote.api.MovieApiService
import com.example.filmes.data.remote.dto.MovieDetailsDto
import com.example.filmes.data.remote.dto.MoviesResponseDto
import com.example.filmes.core.constants.ApiConstants

class MovieRemoteDataSource(
    private val apiService: MovieApiService
) {
    suspend fun getPopularMovies(page: Int): MoviesResponseDto {
        return apiService.getPopularMovies(page, BuildConfig.TMDB_API_KEY)
    }
    
    suspend fun searchMovies(query: String, page: Int): MoviesResponseDto {
        return apiService.searchMovies(query, page, BuildConfig.TMDB_API_KEY)
    }
    
    suspend fun getMovieDetails(movieId: Int): MovieDetailsDto {
        return apiService.getMovieDetails(movieId, BuildConfig.TMDB_API_KEY)
    }
}
