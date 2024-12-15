package com.kangengineering.HW5.views

import com.kangengineering.HW5.data.LocationData

data class MapUIState(
    val isLoading: Boolean = false,
    val mapdata: List<LocationData> = emptyList(),
    val error: String? = null
)
