package edu.weather.wreport.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiPost(
        @SerializedName("value")  val value: Double,
        @SerializedName("year") val year: Int,
        @SerializedName("month") val month: Int
)