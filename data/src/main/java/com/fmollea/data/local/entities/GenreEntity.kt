package com.fmollea.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class GenreEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "remote_id")
    val remoteId: Long = -1,
    @ColumnInfo(name = "genre")
    val genre: String = "",
)
