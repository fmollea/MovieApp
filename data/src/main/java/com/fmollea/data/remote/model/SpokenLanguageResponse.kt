package com.fmollea.data.remote.model

import com.google.gson.annotations.SerializedName

data class SpokenLanguageResponse(
    @SerializedName("english_name") val englishName: String,
    @SerializedName("iso_639_1") val iso639_1: String,
    @SerializedName("name") val name: String
)
