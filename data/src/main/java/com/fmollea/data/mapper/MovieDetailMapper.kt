package com.fmollea.data.mapper

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
}
