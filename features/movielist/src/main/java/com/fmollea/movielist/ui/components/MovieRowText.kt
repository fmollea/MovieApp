package com.fmollea.movielist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MovieRowText(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .background(
                color = Color(0x80000000),
                shape = RoundedCornerShape(4.dp),
            )
            .padding(4.dp),
        color = Color.White,
        text = title
    )
}
