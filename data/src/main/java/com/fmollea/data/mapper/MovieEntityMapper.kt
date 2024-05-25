package com.fmollea.data.mapper

import com.fmollea.data.local.entities.MovieEntity
import com.fmollea.domain.model.Movie

class MovieEntityMapper {
    fun mapFromEntity(entity: MovieEntity): Movie {
        return Movie(
            id = entity.id,
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
            voteCount = entity.voteCount
        )
    }

    fun mapFromEntityList(entities: List<MovieEntity>): List<Movie> {
        return entities.map { mapFromEntity(it) }
    }

    private fun mapToEntity(movie: Movie): MovieEntity {
        return MovieEntity(
            id = movie.id,
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
            voteCount = movie.voteCount
        )
    }

    fun mapToEntityList(movies: List<Movie>): List<MovieEntity> {
        return movies.map { mapToEntity(it) }
    }
}
