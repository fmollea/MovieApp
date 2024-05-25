package com.fmollea.data.mapper

import com.fmollea.data.remote.model.MovieResponse
import com.fmollea.domain.model.Movie

class MovieMapper {
    private fun mapFromResponse(response: MovieResponse): Movie {
        return Movie(
            id = response.id.toLong(),
            adult = response.adult,
            backdropPath = response.backdropPath ?: "",
            originalLanguage = response.originalLanguage,
            overview = response.overview,
            popularity = response.popularity,
            posterPath = response.posterPath ?: "",
            releaseDate = response.releaseDate,
            title = response.title,
            video = response.video,
            voteAverage = response.voteAverage,
            voteCount = response.voteCount
        )
    }

    fun mapFromResponseList(movies: List<MovieResponse>): List<Movie> {
        return movies.map { mapFromResponse(it) }
    }
}
