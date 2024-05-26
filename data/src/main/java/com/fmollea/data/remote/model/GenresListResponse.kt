package com.fmollea.data.remote.model

import com.google.gson.annotations.SerializedName

data class GenresListResponse(
    @SerializedName("genres") val genres: List<GenreResponse>
)
