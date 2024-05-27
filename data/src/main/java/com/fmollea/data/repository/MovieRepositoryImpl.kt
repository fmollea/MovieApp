package com.fmollea.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.fmollea.data.local.LocalDataSource
import com.fmollea.data.mapper.GenreEntityMapper
import com.fmollea.data.mapper.GenreMapper
import com.fmollea.data.mapper.MovieDetailMapper
import com.fmollea.data.mapper.MovieEntityMapper
import com.fmollea.data.remote.RemoteDataSource
import com.fmollea.data.remotemediator.MovieRemoteMediator
import com.fmollea.domain.model.Movie
import com.fmollea.domain.model.MovieDetail
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
    private val genreEntityMapper: GenreEntityMapper,
    private val movieDetailMapper: MovieDetailMapper
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
                val genre = genreMapper.mapFromResponse(genreResponse)
                localDataSource.insertGenres(genreEntityMapper.mapToEntityList(genre))
                genre
            } catch (e: Exception) {
                val genreEntities = localDataSource.getGenres()
                genreEntityMapper.mapFromGenreList(genreEntities)
            }
        }
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return withContext(Dispatchers.IO) {
            val response = remoteDataSource.getMovieDetail(movieId)
            val movieDetail = movieDetailMapper.mapFromResponse(response)
            movieDetail
        }
    }

    override suspend fun insertMovieDetail(movieDetail: MovieDetail) {
        withContext(Dispatchers.IO) {
            val entity = movieDetailMapper.toEntity(movieDetail)
            localDataSource.insertMovieDetail(entity)
        }
    }

    override suspend fun deleteMovieDetail(movieDetail: MovieDetail) {
        withContext(Dispatchers.IO) {
            val entity = movieDetailMapper.toEntity(movieDetail)
            localDataSource.deleteMovieDetail(entity)
        }
    }

    override suspend fun getAllMovieDetail(): List<MovieDetail> {
        return withContext(Dispatchers.IO) {
            val moviesDetailEntity = localDataSource.getAllMovieDetail()
            moviesDetailEntity.map { movieDetailMapper.fromEntity(it) }
        }
    }

    override suspend fun isMovieDetailSaved(movieDetailId: Long): Boolean {
        return withContext(Dispatchers.IO) {
            localDataSource.isMovieDetailSaved(movieDetailId)
        }
    }
}

private const val MOVIES_PER_PAGE = 20
