package com.fmollea.data.mapper

import com.fmollea.data.remote.model.GenresListResponse

class GenreMapper {

    fun mapFromResponse(response: GenresListResponse): HashMap<Int, String> {
        val genreMap = HashMap<Int, String>()
        response.genres.forEach {
            genreMap[it.id] = it.name
        }
        return genreMap
    }
}
