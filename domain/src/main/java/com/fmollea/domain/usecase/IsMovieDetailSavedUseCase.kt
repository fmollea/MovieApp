package com.fmollea.domain.usecase

import com.fmollea.domain.repository.MovieRepository
import javax.inject.Inject

class IsMovieDetailSavedUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend fun invoke(movieDetailId: Long): Boolean = repository.isMovieDetailSaved(movieDetailId)
}
