package com.fmollea.data.repository

import com.fmollea.data.local.LocalDataSource
import com.fmollea.data.mapper.GenreEntityMapper
import com.fmollea.data.mapper.GenreMapper
import com.fmollea.data.mapper.MovieEntityMapper
import com.fmollea.data.mapper.MovieMapper
import com.fmollea.data.remote.RemoteDataSource
import com.fmollea.domain.model.Movie
import com.fmollea.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val movieMapper: MovieMapper,
    private val movieEntityMapper: MovieEntityMapper,
    private val genreMapper: GenreMapper,
    private val genreEntityMapper: GenreEntityMapper
) : MovieRepository {

    override suspend fun getMovies(): List<Movie> {
        return withContext(Dispatchers.IO) {
            try {
                val movieResponses = remoteDataSource.getMovies()
                val movies = movieMapper.mapFromResponseList(movieResponses.results)
                localDataSource.insertMovies(movieEntityMapper.mapToEntityList(movies))
                movies
            } catch (e: Exception) {
                val movieEntities = localDataSource.getMovies()
                movieEntityMapper.mapFromEntityList(movieEntities)
            }
        }
    }

    override suspend fun getGenres(): HashMap<Int, String> {
        return withContext(Dispatchers.IO) {
            try {
                val genreResponse = remoteDataSource.getGenres()
                val genreMap = genreMapper.mapFromResponse(genreResponse)
                localDataSource.insertGenres(genreEntityMapper.mapToEntityList(genreMap))
                genreMap
            } catch (e: Exception) {
                val genreEntities = localDataSource.getGenres()
                genreEntityMapper.mapFromGenreList(genreEntities)
            }
        }
    }
}
