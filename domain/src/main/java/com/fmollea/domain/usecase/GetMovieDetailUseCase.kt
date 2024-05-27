package com.fmollea.domain.usecase

import com.fmollea.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend fun invoke(movieId: Int) = repository.getMovieDetail(movieId)
}
