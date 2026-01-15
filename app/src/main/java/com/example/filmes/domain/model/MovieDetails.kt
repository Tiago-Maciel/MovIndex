package com.example.filmes.domain.model

import com.example.filmes.core.constants.ApiConstants

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String?,
    val voteAverage: Double,
    val voteCount: Int,
    val runtime: Int?,
    val genres: List<Genre>
) {
    val posterUrl: String
        get() = if (posterPath != null) {
            "${ApiConstants.IMAGE_BASE_URL}/${ApiConstants.POSTER_SIZE}$posterPath"
        } else {
            ""
        }
    
    val backdropUrl: String
        get() = if (backdropPath != null) {
            "${ApiConstants.IMAGE_BASE_URL}/${ApiConstants.BACKDROP_SIZE}$backdropPath"
        } else {
            ""
        }
    
    val genresText: String
        get() = genres.joinToString(", ") { it.name }
}

data class Genre(
    val id: Int,
    val name: String
)
