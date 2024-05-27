package com.fmollea.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_detail")
data class MovieDetailEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
)
