package com.example.filmes.di

import android.app.Application
import androidx.room.Room
import com.example.filmes.core.database.DatabaseConstants
import com.example.filmes.data.datasource.local.MovieLocalDataSource
import com.example.filmes.data.datasource.remote.MovieRemoteDataSource
import com.example.filmes.data.local.database.MovieDatabase
import com.example.filmes.data.remote.api.MovieApiService
import com.example.filmes.data.remote.api.RetrofitClient
import com.example.filmes.data.repository.MovieRepository
import com.example.filmes.domain.repository.IMovieRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<MovieApiService> { RetrofitClient.movieApiService }
    
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            DatabaseConstants.DATABASE_NAME
        ).build()
    }
    
    single { get<MovieDatabase>().favoriteMovieDao() }
    
    single<MovieRemoteDataSource> {
        MovieRemoteDataSource(apiService = get())
    }
    
    single<MovieLocalDataSource> {
        MovieLocalDataSource(favoriteDao = get())
    }
    
    single<IMovieRepository> {
        MovieRepository(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }
}
