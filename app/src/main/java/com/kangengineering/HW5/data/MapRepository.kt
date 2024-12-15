package com.kangengineering.HW5.data

import android.content.Context
import kotlinx.coroutines.flow.Flow

interface MapRepository {
    suspend fun getMapData(): Flow<List<LocationData>>
    suspend fun fetchRemoteMapData()
    fun getDeviceLocation(context: Context)
}