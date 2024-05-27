package com.fmollea.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductionCountryResponse(
    @SerializedName("iso_3166_1") val iso3166_1: String,
    @SerializedName("name") val name: String
)
