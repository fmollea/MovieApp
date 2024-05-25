package com.fmollea.movieapp.di

import android.content.Context
import androidx.room.Room
import com.fmollea.data.local.LocalDataSource
import com.fmollea.data.local.dao.MovieDao
import com.fmollea.data.local.database.AppDataBase
import com.fmollea.data.mapper.MovieEntityMapper
import com.fmollea.data.mapper.MovieMapper
import com.fmollea.data.remote.RemoteDataSource
import com.fmollea.data.remote.api.MovieApi
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
        return MovieMapper()
    }

    @Singleton
    @Provides
    fun provideMovieEntityMapper(): MovieEntityMapper {
        return MovieEntityMapper()
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(
        movieDao: MovieDao,
    ): LocalDataSource {
        return LocalDataSource(movieDao)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        movieApi: MovieApi
    ): RemoteDataSource {
        return RemoteDataSource(movieApi)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        movieMapper: MovieMapper,
        movieEntityMapper: MovieEntityMapper
    ): MovieRepository {
        return MovieRepositoryImpl(remoteDataSource, localDataSource, movieMapper, movieEntityMapper)
    }
}
