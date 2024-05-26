package com.fmollea.movielist.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.fmollea.movielist.viewmodel.MovieListViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieListViewModel = hiltViewModel(),
) {

    LaunchedEffect(Unit) {
        viewModel.getMovieList()
    }

    val state by viewModel.state.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
       when(state) {
            is MovieListState.Loading -> Text("loading")
            is MovieListState.Success -> Text("success")
            is MovieListState.Error -> Text("erorr")
       }
    }
}
