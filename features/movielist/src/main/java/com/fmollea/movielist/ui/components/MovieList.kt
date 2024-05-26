package com.fmollea.movielist.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fmollea.domain.model.Movie

@Composable
fun MovieList(
    movies: List<Movie>,
    genres: HashMap<Int, String>,
    onNavigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn {
        items(movies) {
            val genre = genres.get(it.genreId) ?: ""
            MovieRow(movie = it, genre = genre, onNavigateToDetail = onNavigateToDetail)
        }
    }
}
