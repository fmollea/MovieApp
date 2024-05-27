package com.fmollea.moviedetail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.fmollea.moviedetail.state.MovieDetailState
import com.fmollea.moviedetail.ui.components.Loading
import com.fmollea.moviedetail.ui.components.MovieDetailContent
import com.fmollea.moviedetail.viewmodel.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    movieId: Long,
    modifier: Modifier = Modifier,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getMovieDetail(movieId.toInt())
        viewModel.isSubscribeMovie(movieId)
    }

    val movieDetailState by viewModel.movieDetailState.collectAsState()
    val isSubscribe by viewModel.isSubscribeState.collectAsState()

    when (val state = movieDetailState) {
        is MovieDetailState.Loading -> {
            Loading()
        }
        is MovieDetailState.Error -> {
        }
        is MovieDetailState.Success -> {
            val movieDetail = state.movieDetail
            MovieDetailContent(
                movieDetail = movieDetail,
                isSubscribe = isSubscribe,
                onSubscribe = {
                    viewModel.subscribeMovie(movieDetail)
                },
                modifier = modifier
            )
        }
    }
}
