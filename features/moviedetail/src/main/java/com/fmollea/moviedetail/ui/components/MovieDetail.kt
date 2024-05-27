package com.fmollea.moviedetail.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.fmollea.domain.model.MovieDetail

@Composable
fun MovieDetailContent(
    movieDetail: MovieDetail,
    onSubscribe: (MovieDetail) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            val painter: Painter = rememberAsyncImagePainter(model = movieDetail.posterPath)
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
                    .height(350.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
        item {
            Text(
                modifier = Modifier.padding(8.dp),
                text = movieDetail.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
            )
        }
        item {
            Text(
                modifier = Modifier.padding(8.dp),
                text = movieDetail.releaseDate.take(4),
                fontSize = 18.sp
            )
        }
        item {
            Button(onClick = {
                onSubscribe(movieDetail)
            }) {
                Text(text = "Subscribe")
            }
        }
        item {
            Text(
                modifier = Modifier.padding(4.dp),
                text = "Overview"
            )
        }
        item {
            Text(
                modifier = Modifier.padding(4.dp),
                text = movieDetail.overview
            )
        }
    }
}
