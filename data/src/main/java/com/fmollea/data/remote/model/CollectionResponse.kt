package com.fmollea.data.remote.model

import com.google.gson.annotations.SerializedName

data class CollectionResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?
)
