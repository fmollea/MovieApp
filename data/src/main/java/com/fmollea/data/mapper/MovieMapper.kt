package com.fmollea.data.mapper

import com.fmollea.data.remote.model.MovieResponse
import com.fmollea.domain.model.Movie
import javax.inject.Inject

class MovieMapper @Inject constructor(
    private val baseImageUrl: String
) {
    private fun mapFromResponse(response: MovieResponse): Movie {
        return Movie(
            id = response.id.toLong(),
            adult = response.adult,
            backdropPath = response.backdropPath?.let {
                baseImageUrl + it
            } ?: "",
            originalLanguage = response.originalLanguage,
            overview = response.overview,
            popularity = response.popularity,
            posterPath = response.posterPath?.let {
                baseImageUrl + it
            } ?: "",
            releaseDate = response.releaseDate,
            title = response.title,
            video = response.video,
            voteAverage = response.voteAverage,
            voteCount = response.voteCount,
            genreId = response.genreIds.first()
        )
    }

    fun mapFromResponseList(movies: List<MovieResponse>): List<Movie> {
        return movies.map { mapFromResponse(it) }
    }
}
