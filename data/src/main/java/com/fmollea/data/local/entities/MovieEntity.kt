package com.fmollea.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "remote_id")
    val remoteId: Long = -1,
    @ColumnInfo(name = "adult")
    val adult: Boolean = false,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String = "",
    @ColumnInfo(name = "original_language")
    val originalLanguage: String = "",
    @ColumnInfo(name = "overview")
    val overview: String = "",
    @ColumnInfo(name = "popularity")
    val popularity: Double = -1.0,
    @ColumnInfo(name = "poster_path")
    val posterPath: String = "",
    @ColumnInfo(name = "release_date")
    val releaseDate: String = "",
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "video")
    val video: Boolean = false,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double = -1.0,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int = -1,
    @ColumnInfo(name = "genre_id")
    val genreId: Int = -1
)
