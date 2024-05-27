package com.fmollea.domain.usecase

import com.fmollea.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val repository: MovieRepository,
) {
    fun invoke() = repository.getMovies()
}
