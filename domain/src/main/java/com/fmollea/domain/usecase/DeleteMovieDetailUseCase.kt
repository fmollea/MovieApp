package com.fmollea.domain.usecase

import com.fmollea.domain.model.MovieDetail
import com.fmollea.domain.repository.MovieRepository
import javax.inject.Inject

class DeleteMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend fun invoke(movieDetail: MovieDetail) = repository.deleteMovieDetail(movieDetail)
}
