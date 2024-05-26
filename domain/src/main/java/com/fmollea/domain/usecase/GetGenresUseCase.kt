package com.fmollea.domain.usecase

import com.fmollea.domain.repository.MovieRepository
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend fun invoke() = repository.getGenres()
}
