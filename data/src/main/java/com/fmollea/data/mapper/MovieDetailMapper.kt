package com.fmollea.data.mapper

import com.fmollea.data.local.entities.MovieDetailEntity
import com.fmollea.data.remote.model.MovieDetailResponse
import com.fmollea.domain.model.MovieDetail
import javax.inject.Inject

class MovieDetailMapper @Inject constructor(
    private val baseImageUrl: String
) {
    fun mapFromResponse(response: MovieDetailResponse): MovieDetail {
        return MovieDetail(
            backdropPath = response.backdropPath?.let {
                baseImageUrl + it
            } ?: "",
            id = response.id,
            posterPath = response.posterPath?.let {
                baseImageUrl + it
            } ?: "",
            title = response.title,
            overview = response.overview,
            releaseDate = response.releaseDate
        )
    }

    fun toEntity(movieDetail: MovieDetail): MovieDetailEntity {
        return MovieDetailEntity(
            id = movieDetail.id,
            backdropPath = movieDetail.backdropPath,
            posterPath = movieDetail.posterPath,
            title = movieDetail.title,
            overview = movieDetail.overview,
            releaseDate = movieDetail.releaseDate
        )
    }

    fun fromEntity(movieDetailEntity: MovieDetailEntity): MovieDetail {
        return MovieDetail(
            id = movieDetailEntity.id,
            backdropPath = movieDetailEntity.backdropPath,
            posterPath = movieDetailEntity.posterPath,
            title = movieDetailEntity.title,
            overview = movieDetailEntity.overview,
            releaseDate = movieDetailEntity.releaseDate
        )
    }
}
