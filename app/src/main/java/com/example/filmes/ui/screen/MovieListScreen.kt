package com.example.filmes.ui.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.filmes.domain.model.Movie
import com.example.filmes.ui.presentation.component.MovieCard
import com.example.filmes.ui.presentation.component.SearchBar
import com.example.filmes.ui.presentation.viewmodel.MovieListViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    onMovieClick: (Int) -> Unit,
    onFavoritesClick: () -> Unit,
    viewModel: MovieListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val moviesFlow by viewModel.movies.collectAsStateWithLifecycle()
    val movies = moviesFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Filmes") },
                actions = {
                    IconButton(onClick = onFavoritesClick) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favoritos"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchBar(
                query = uiState.searchQuery,
                onQueryChange = { viewModel.searchMovies(it) },
                onClearClick = { viewModel.clearSearch() },
                modifier = Modifier.padding(16.dp)
            )

            when {
                movies.loadState.refresh is androidx.paging.LoadState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                movies.loadState.refresh is androidx.paging.LoadState.Error -> {
                    val error = (movies.loadState.refresh as androidx.paging.LoadState.Error).error
                    ErrorMessage(
                        message = error.message ?: "Erro ao carregar filmes",
                        onRetry = { movies.retry() }
                    )
                }
                else -> {
                    MovieGrid(
                        movies = movies,
                        onMovieClick = onMovieClick
                    )
                }
            }
        }
    }
}

@Composable
fun MovieGrid(
    movies: LazyPagingItems<Movie>,
    onMovieClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies.itemCount) { index ->
            movies[index]?.let { movie ->
                MovieCard(
                    movie = movie,
                    onClick = { onMovieClick(movie.id) }
                )
            }
        }

        item {
            when (movies.loadState.append) {
                is androidx.paging.LoadState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is androidx.paging.LoadState.Error -> {
                    val error = (movies.loadState.append as androidx.paging.LoadState.Error).error
                    ErrorMessage(
                        message = error.message ?: "Erro ao carregar mais filmes",
                        onRetry = { movies.retry() }
                    )
                }
                else -> {}
            }
        }
    }
}

@Composable
fun ErrorMessage(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Tentar novamente")
        }
    }
}
