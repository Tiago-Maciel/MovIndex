package com.example.filmes.di

import com.example.filmes.ui.presentation.viewmodel.FavoritesViewModel
import com.example.filmes.ui.presentation.viewmodel.MovieDetailsViewModel
import com.example.filmes.ui.presentation.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieListViewModel(repository = get()) }
    viewModel { MovieDetailsViewModel(repository = get()) }
    viewModel { FavoritesViewModel(repository = get()) }
}
