package com.kangengineering.HW5.data


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LocationData")
data class LocationDataEntity(
    @PrimaryKey
    val id: String,
    val latitude: String,
    val longitude: String,

)
