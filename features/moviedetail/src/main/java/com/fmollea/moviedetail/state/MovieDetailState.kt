package com.fmollea.moviedetail.state

import com.fmollea.domain.model.MovieDetail

sealed class MovieDetailState {
    data object Loading : MovieDetailState()
    data class Success(val movieDetail: MovieDetail) : MovieDetailState()
    data class Error(val message: String) : MovieDetailState()
}
