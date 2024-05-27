package com.fmollea.moviedetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmollea.domain.model.MovieDetail
import com.fmollea.domain.usecase.DeleteMovieDetailUseCase
import com.fmollea.domain.usecase.GetMovieDetailUseCase
import com.fmollea.domain.usecase.InsertMovieDetailUseCase
import com.fmollea.domain.usecase.IsMovieDetailSavedUseCase
import com.fmollea.moviedetail.state.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val deleteMovieDetailUseCase: DeleteMovieDetailUseCase,
    private val insertMovieDetailUseCase: InsertMovieDetailUseCase,
    private val isMovieDetailSavedUseCase: IsMovieDetailSavedUseCase
) : ViewModel() {

    private val _movieDetailState = MutableStateFlow<MovieDetailState>(MovieDetailState.Loading)
    val movieDetailState: StateFlow<MovieDetailState> = _movieDetailState.asStateFlow()

    private val _isSubscribeState = MutableStateFlow(false)
    val isSubscribeState: StateFlow<Boolean> = _isSubscribeState.asStateFlow()

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            _movieDetailState.value = MovieDetailState.Loading
            try {
                val movieDetail = getMovieDetailUseCase.invoke(movieId)
                _movieDetailState.value = MovieDetailState.Success(movieDetail)
            } catch (e: Exception) {
                _movieDetailState.value = MovieDetailState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun isSubscribeMovie(movieDetailId: Long) {
        viewModelScope.launch {
            _isSubscribeState.value = isMovieDetailSavedUseCase.invoke(movieDetailId)
        }
    }

    fun subscribeMovie(movieDetail: MovieDetail) {
        viewModelScope.launch {
            if (_isSubscribeState.value) {
                deleteMovieDetailUseCase.invoke(movieDetail)
                _isSubscribeState.value = false
            } else {
                insertMovieDetailUseCase.invoke(movieDetail)
                _isSubscribeState.value = true
            }
        }
    }
}