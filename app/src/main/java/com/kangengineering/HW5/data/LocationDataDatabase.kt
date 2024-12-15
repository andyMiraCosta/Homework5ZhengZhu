package com.kangengineering.HW5.data


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [LocationDataEntity::class],
    version = 1,

)
@TypeConverters(LocationDataTypeConverters::class)
abstract class LocationDataDatabase: RoomDatabase() {
    abstract fun locationDataDao(): LocationDataDao
}