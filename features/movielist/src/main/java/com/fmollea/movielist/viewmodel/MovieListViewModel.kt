package com.fmollea.movielist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fmollea.domain.model.Movie
import com.fmollea.domain.model.MovieDetail
import com.fmollea.domain.usecase.GetAllMovieDetailsUseCase
import com.fmollea.domain.usecase.GetGenresUseCase
import com.fmollea.domain.usecase.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListUseCase: GetMovieListUseCase,
    private val genresUseCase: GetGenresUseCase,
    private val getAllMovieDetailsUseCase: GetAllMovieDetailsUseCase
) : ViewModel() {

    private val _genresState = MutableStateFlow<HashMap<Int, String>>(hashMapOf())
    val genresState = _genresState.asStateFlow()

    private val _moviesSubscribeState = MutableStateFlow<List<MovieDetail>>(mutableListOf())
    val moviesSubscribeState: StateFlow<List<MovieDetail>> = _moviesSubscribeState.asStateFlow()

    val movies: Flow<PagingData<Movie>> = movieListUseCase.invoke().cachedIn(viewModelScope)

    fun getGenres() {
        viewModelScope.launch {
            val genres = genresUseCase.invoke()
            _genresState.value = genres
        }
    }

    fun getAllDetailMovies() {
        viewModelScope.launch {
            _moviesSubscribeState.value = getAllMovieDetailsUseCase.invoke()
        }
    }
}
