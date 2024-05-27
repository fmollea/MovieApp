package com.fmollea.movielist.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
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
    }

    val movies = viewModel.movies.collectAsLazyPagingItems()
    val genres by viewModel.genresState.collectAsState()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        MovieList(
            movies = movies,
            genres = genres,
            onNavigateToDetail = onNavigateToDetail
        )
   }
}
