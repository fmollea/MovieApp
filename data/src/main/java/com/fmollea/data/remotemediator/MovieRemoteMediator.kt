package com.fmollea.data.remotemediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.fmollea.data.local.LocalDataSource
import com.fmollea.data.local.entities.MovieEntity
import com.fmollea.data.mapper.MovieEntityMapper
import com.fmollea.data.mapper.MovieMapper
import com.fmollea.data.remote.RemoteDataSource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val movieMapper: MovieMapper,
    private val movieEntityMapper: MovieEntityMapper,
) : RemoteMediator<Int, MovieEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try {
            val page = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    (lastItem.id / state.config.pageSize) + 1
                }
            }

            val response = remoteDataSource.getMovies(page.toInt())
            val movies = movieMapper.mapFromResponseList(response.results)

            if(loadType == LoadType.REFRESH) {
                localDataSource.clearAndInsertMovies(movieEntityMapper.mapToEntityList(movies))
            } else {
                localDataSource.insertMovies(movieEntityMapper.mapToEntityList(movies))
            }

            MediatorResult.Success(endOfPaginationReached = movies.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
