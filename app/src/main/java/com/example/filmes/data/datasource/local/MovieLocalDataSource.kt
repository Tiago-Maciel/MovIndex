package com.example.filmes.data.datasource.local

import com.example.filmes.data.local.database.FavoriteMovie
import com.example.filmes.data.local.database.FavoriteMovieDao
import kotlinx.coroutines.flow.Flow

class MovieLocalDataSource(
    private val favoriteDao: FavoriteMovieDao
) {
    fun getAllFavorites(): Flow<List<FavoriteMovie>> {
        return favoriteDao.getAllFavorites()
    }
    
    suspend fun getFavoriteById(movieId: Int): FavoriteMovie? {
        return favoriteDao.getFavoriteById(movieId)
    }
    
    suspend fun insertFavorite(favorite: FavoriteMovie) {
        favoriteDao.insertFavorite(favorite)
    }
    
    suspend fun deleteFavorite(movieId: Int) {
        favoriteDao.deleteFavorite(movieId)
    }
    
    suspend fun isFavorite(movieId: Int): Boolean {
        return favoriteDao.isFavorite(movieId)
    }
}
