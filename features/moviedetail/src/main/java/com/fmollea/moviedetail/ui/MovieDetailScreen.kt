package com.fmollea.moviedetail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MovieDetailScreen(
    movieId: Long,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Text(
            modifier = Modifier.align(alignment = Alignment.Center),
            text = "Hello movie detail the movie id is: $movieId"
        )
    }
}
