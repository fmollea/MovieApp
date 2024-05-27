package com.fmollea.movielist.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.fmollea.domain.model.Movie
import com.fmollea.domain.model.MovieDetail

@Composable
fun MovieList(
    movies: LazyPagingItems<Movie>,
    genres: HashMap<Int, String>,
    subscribeMovies: List<MovieDetail>,
    onNavigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        if (subscribeMovies.isNotEmpty()) {
            Text(text = "My Subscriptions")
            ShowSubscribeMovies(subscribeMovies, onNavigateToDetail)
        }
        Text(text = "The most popular")
        ShowMovieList(movies, genres, onNavigateToDetail, modifier)
    }
}

@Composable
private fun ShowSubscribeMovies(
    subscribeMovies: List<MovieDetail>,
    onNavigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier.padding(4.dp)) {
        items(subscribeMovies.count()) {
            val movieDetail = subscribeMovies[it]
            ShowMovieRow(movieDetail, onNavigateToDetail)
        }
    }
}

@Composable
private fun ShowMovieRow(
    movieDetail: MovieDetail,
    onNavigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .clickable { onNavigateToDetail(movieDetail.id) }
    ) {
        val painter: Painter = rememberAsyncImagePainter(model = movieDetail.posterPath)
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.width(100.dp).height(180.dp)
        )

    }
}

@Composable
private fun ShowMovieList(
    movies: LazyPagingItems<Movie>,
    genres: HashMap<Int, String>,
    onNavigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        item {
            Spacer(modifier = Modifier.height(4.dp))
        }
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