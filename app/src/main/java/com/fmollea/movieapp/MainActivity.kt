package com.fmollea.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fmollea.movieapp.ui.theme.MovieAppTheme
import com.fmollea.movielist.ui.MovieListScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = MovieList
                ) {
                    composable<MovieList> { MovieListScreen(
                    ) }
                }
            }
        }
    }
}

@Serializable
object MovieList
