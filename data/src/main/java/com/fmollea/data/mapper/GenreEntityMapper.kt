package com.fmollea.data.mapper

import com.fmollea.data.local.entities.GenreEntity

class GenreEntityMapper {

    fun mapToEntityList(genreMap: HashMap<Int, String>): List<GenreEntity> {
        val genreEntityList = mutableListOf<GenreEntity>()
        genreMap.forEach { (remoteId, genreName) ->
            genreEntityList.add(GenreEntity(remoteId = remoteId.toLong(), genre = genreName))
        }
        return genreEntityList
    }

    fun mapFromGenreList(genreEntityList: List<GenreEntity>): HashMap<Int, String> {
        val genreMap = HashMap<Int, String>()
        genreEntityList.forEach {
            genreMap[it.remoteId.toInt()] = it.genre
        }
        return genreMap
    }
}
