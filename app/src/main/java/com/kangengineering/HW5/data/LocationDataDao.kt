package com.kangengineering.HW5.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(locationDataEntity: LocationDataEntity)

    @Query("SELECT * FROM LocationData")
    fun getMapData(): Flow<List<LocationDataEntity>>


}