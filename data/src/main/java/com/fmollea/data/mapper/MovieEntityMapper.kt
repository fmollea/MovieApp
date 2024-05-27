package com.fmollea.data.mapper

import com.fmollea.data.local.entities.MovieEntity
import com.fmollea.domain.model.Movie

class MovieEntityMapper {
    fun mapFromEntity(entity: MovieEntity): Movie {
        return Movie(
            id = entity.remoteId,
            adult = entity.adult,
            backdropPath = entity.backdropPath,
            originalLanguage = entity.originalLanguage,
            overview = entity.overview,
            popularity = entity.popularity,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            title = entity.title,
            video = entity.video,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount,
            genreId = entity.genreId
        )
    }

    private fun mapToEntity(movie: Movie): MovieEntity {
        return MovieEntity(
            remoteId = movie.id,
            adult = movie.adult,
            backdropPath = movie.backdropPath,
            originalLanguage = movie.originalLanguage,
            overview = movie.overview,
            popularity = movie.popularity,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            title = movie.title,
            video = movie.video,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount,
            genreId = movie.genreId
        )
    }

    fun mapToEntityList(movies: List<Movie>): List<MovieEntity> {
        return movies.map { mapToEntity(it) }
    }
}
