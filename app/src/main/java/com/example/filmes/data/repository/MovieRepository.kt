package com.example.filmes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.filmes.data.datasource.local.MovieLocalDataSource
import com.example.filmes.data.datasource.remote.MovieRemoteDataSource
import com.example.filmes.data.local.database.FavoriteMovie
import com.example.filmes.data.mapper.toDomain
import com.example.filmes.data.paging.MoviePagingSource
import com.example.filmes.data.paging.SearchMoviePagingSource
import com.example.filmes.domain.model.Movie
import com.example.filmes.domain.model.MovieDetails
import com.example.filmes.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) : IMovieRepository {
    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(remoteDataSource)
            }
        ).flow.map { pagingData ->
            pagingData.map { dto -> dto.toDomain() }
        }
    }

    override fun searchMovies(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchMoviePagingSource(remoteDataSource, query)
            }
        ).flow.map { pagingData ->
            pagingData.map { dto -> dto.toDomain() }
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Result<MovieDetails> {
        return try {
            val response = remoteDataSource.getMovieDetails(movieId)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getAllFavorites(): Flow<List<Movie>> {
        return localDataSource.getAllFavorites().map { favorites ->
            favorites.map { favorite ->
                Movie(
                    id = favorite.id,
                    title = favorite.title,
                    overview = favorite.overview,
                    posterPath = favorite.posterPath,
                    backdropPath = favorite.backdropPath,
                    releaseDate = favorite.releaseDate,
                    voteAverage = favorite.voteAverage,
                    voteCount = favorite.voteCount
                )
            }
        }
    }

    override suspend fun addToFavorites(movie: Movie) {
        val favorite = FavoriteMovie(
            id = movie.id,
            title = movie.title,
            overview = movie.overview,
            posterPath = movie.posterPath,
            backdropPath = movie.backdropPath,
            releaseDate = movie.releaseDate,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount
        )
        localDataSource.insertFavorite(favorite)
    }

    override suspend fun removeFromFavorites(movieId: Int) {
        localDataSource.deleteFavorite(movieId)
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return localDataSource.isFavorite(movieId)
    }
}
