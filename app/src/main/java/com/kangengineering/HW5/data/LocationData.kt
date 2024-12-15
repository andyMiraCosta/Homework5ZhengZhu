package com.kangengineering.HW5.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationData(
    @SerialName("_id")
    val id: String,
    @SerialName("latitude")
    val latitude: String = "",
    @SerialName("longitude")
    val longitude: String = "",
)