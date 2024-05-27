package com.fmollea.movielist.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.fmollea.domain.model.Movie

@Composable
fun MovieList(
    movies: LazyPagingItems<Movie>,
    genres: HashMap<Int, String>,
    onNavigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(movies.itemCount) { item ->
            val movie = movies[item]
            movie?.let {
                val genre = genres.get(it.genreId) ?: ""
                MovieRow(movie = it, genre = genre, onNavigateToDetail = onNavigateToDetail)
            }
        }

        when (movies.loadState.append) {
            is LoadState.Loading -> {
                item { Loading() }
            }
            is LoadState.Error -> {
                item { Text("Error loading more movies") }
            }
            else -> {
                item { Text("Error loading more movies") }
            }
        }
    }
}
