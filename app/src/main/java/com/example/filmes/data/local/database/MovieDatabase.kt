package com.example.filmes.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.filmes.core.database.DatabaseConstants

@Database(
    entities = [FavoriteMovie::class],
    version = DatabaseConstants.DATABASE_VERSION,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}
