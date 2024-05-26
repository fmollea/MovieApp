package com.fmollea.movielist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmollea.domain.usecase.GetMovieListUseCase
import com.fmollea.movielist.state.MovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListUseCase: GetMovieListUseCase
) : ViewModel() {

    private val _state =  MutableStateFlow<MovieListState>(MovieListState.Loading)
    val state = _state.asStateFlow()

    fun getMovieList() {
        viewModelScope.launch {
            _state.value = MovieListState.Loading
            try {
                val movies = movieListUseCase.invoke()
                _state.value = MovieListState.Success(movies)
            } catch (e: Exception) {
                _state.value = MovieListState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}
