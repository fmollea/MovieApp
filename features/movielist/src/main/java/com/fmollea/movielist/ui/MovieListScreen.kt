package com.fmollea.movielist.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.fmollea.movielist.state.MovieListState
import com.fmollea.movielist.ui.components.Loading
import com.fmollea.movielist.ui.components.MovieList
import com.fmollea.movielist.viewmodel.MovieListViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MovieListScreen(
    onNavigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MovieListViewModel = hiltViewModel(),
) {

    LaunchedEffect(Unit) {
        viewModel.getGenres()
        viewModel.getMovieList()
    }

    val state by viewModel.state.collectAsState()
    val genres by viewModel.genresState.collectAsState()

    Box(
        modifier = modifier.fillMaxSize(),
       contentAlignment = Alignment.Center
    ) {
       when(state) {
            is MovieListState.Loading -> Loading()
            is MovieListState.Success -> {
                val movies = (state as MovieListState.Success).movieList
                MovieList(
                    movies = movies,
                    genres = genres,
                    onNavigateToDetail = onNavigateToDetail
                )
            }
            is MovieListState.Error -> Text("erorr")
       }
    }
}
