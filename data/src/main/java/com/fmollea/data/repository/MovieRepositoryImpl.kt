package com.fmollea.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.fmollea.data.local.LocalDataSource
import com.fmollea.data.mapper.GenreEntityMapper
import com.fmollea.data.mapper.GenreMapper
import com.fmollea.data.mapper.MovieEntityMapper
import com.fmollea.data.remote.RemoteDataSource
import com.fmollea.data.remotemediator.MovieRemoteMediator
import com.fmollea.domain.model.Movie
import com.fmollea.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val movieRemoteMediator: MovieRemoteMediator,
    private val movieEntityMapper: MovieEntityMapper,
    private val genreMapper: GenreMapper,
    private val genreEntityMapper: GenreEntityMapper
) : MovieRepository {

    override fun getMovies(): Flow<PagingData<Movie>> {
        val pager = Pager(
            config = PagingConfig(pageSize = MOVIES_PER_PAGE),
            remoteMediator = movieRemoteMediator,
            pagingSourceFactory = {
                localDataSource.getMovies()
            }).flow.map {
                it.map { movieEntity ->
                    movieEntityMapper.mapFromEntity(movieEntity)
                }
            }

        return pager
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

private const val MOVIES_PER_PAGE = 20
