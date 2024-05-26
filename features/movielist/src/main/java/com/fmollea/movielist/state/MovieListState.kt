package com.fmollea.movielist.state

import com.fmollea.domain.model.Movie

sealed class MovieListState {
    data object Loading : MovieListState()
    data class Success(val movieList: List<Movie>) : MovieListState()
    data class Error(val message: String) : MovieListState()
}
