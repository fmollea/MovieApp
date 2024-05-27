package com.fmollea.movieapp.di

import android.content.Context
import androidx.room.Room
import com.fmollea.data.local.LocalDataSource
import com.fmollea.data.local.dao.GenreDao
import com.fmollea.data.local.dao.MovieDao
import com.fmollea.data.local.database.AppDataBase
import com.fmollea.data.mapper.GenreEntityMapper
import com.fmollea.data.mapper.GenreMapper
import com.fmollea.data.mapper.MovieEntityMapper
import com.fmollea.data.mapper.MovieMapper
import com.fmollea.data.remote.RemoteDataSource
import com.fmollea.data.remote.api.MovieApi
import com.fmollea.data.remotemediator.MovieRemoteMediator
import com.fmollea.data.repository.MovieRepositoryImpl
import com.fmollea.domain.repository.MovieRepository
import com.fmollea.movieapp.BuildConfig
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "app_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(database: AppDataBase): MovieDao {
        return database.movieDao()
    }

    @Singleton
    @Provides
    fun provideGenreDao(database: AppDataBase): GenreDao {
        return database.genreDao()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieMapper(): MovieMapper {
        return MovieMapper(BuildConfig.BASE_IMAGE_URL)
    }

    @Singleton
    @Provides
    fun provideMovieEntityMapper(): MovieEntityMapper {
        return MovieEntityMapper()
    }

    @Singleton
    @Provides
    fun provideGenreMapper(): GenreMapper {
        return GenreMapper()
    }

    @Singleton
    @Provides
    fun provideGenreEntityMapper(): GenreEntityMapper {
        return GenreEntityMapper()
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(
        appDataBase: AppDataBase,
        movieDao: MovieDao,
        genreDao: GenreDao
    ): LocalDataSource {
        return LocalDataSource(dataBase = appDataBase, movieDao = movieDao, genreDao = genreDao)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        movieApi: MovieApi
    ): RemoteDataSource {
        return RemoteDataSource(movieApi, BuildConfig.API_KEY)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        movieRemoteMediator: MovieRemoteMediator,
        movieEntityMapper: MovieEntityMapper,
        genreMapper: GenreMapper,
        genreEntityMapper: GenreEntityMapper
    ): MovieRepository {
        return MovieRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            movieRemoteMediator = movieRemoteMediator,
            movieEntityMapper = movieEntityMapper,
            genreMapper = genreMapper,
            genreEntityMapper = genreEntityMapper
        )
    }

    @Singleton
    @Provides
    fun provideRemoteMediator(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        movieMapper: MovieMapper,
        movieEntityMapper: MovieEntityMapper,
    ): MovieRemoteMediator {
        return MovieRemoteMediator(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            movieMapper = movieMapper,
            movieEntityMapper = movieEntityMapper
        )
    }
}
