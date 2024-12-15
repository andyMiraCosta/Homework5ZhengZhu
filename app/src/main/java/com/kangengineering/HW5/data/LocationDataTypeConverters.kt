package com.kangengineering.HW5.data

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LocationDataTypeConverters {

    @TypeConverter
    fun convertTagsToString(tags: List<String>): String {
        return Json.encodeToString(tags)
    }

    @TypeConverter
    fun convertStringToTags(tags: String): List<String> {
        return Json.decodeFromString(tags)
    }
}