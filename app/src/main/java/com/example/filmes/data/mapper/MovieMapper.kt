package com.example.filmes.data.mapper

import com.example.filmes.data.remote.dto.MovieDto
import com.example.filmes.data.remote.dto.MovieDetailsDto
import com.example.filmes.domain.model.Movie
import com.example.filmes.domain.model.MovieDetails
import com.example.filmes.domain.model.Genre

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun MovieDetailsDto.toDomain(): MovieDetails {
    return MovieDetails(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        runtime = runtime,
        genres = genres.map { Genre(id = it.id, name = it.name) }
    )
}
