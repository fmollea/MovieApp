package com.fmollea.movielist.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.fmollea.domain.model.Movie

@Composable
fun MovieRow(
    movie: Movie,
    genre: String,
    onNavigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
      modifier = modifier
          .fillMaxWidth()
          .height(200.dp)
          .padding(vertical = 8.dp, horizontal = 16.dp)
          .clickable { onNavigateToDetail(movie.id) }
    ) {
        val painter: Painter = rememberAsyncImagePainter(model = movie.backdropPath)
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize().
            clip(RoundedCornerShape(8.dp))
        )
        if (genre.isNotBlank()) {
            MovieRowText(
                title = genre,
                modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)
            )
        }
        MovieRowText(
            title = movie.title,
            modifier = Modifier.align(Alignment.BottomStart).padding(8.dp)
        )
    }
}
