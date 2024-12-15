package com.kangengineering.HW5.data

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.kangengineering.HW5.views.handleGetLastLocationSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MapRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val locationDataDao: LocationDataDao
): MapRepository {
    override suspend fun getMapData(): Flow<List<LocationData>> {
        return withContext(dispatcher) {
            locationDataDao.getMapData()
                .map { locationDataCached ->
                    locationDataCached.map { locationDataEntity ->
                        LocationData(
                            id = locationDataEntity.id,
                            longitude = locationDataEntity.longitude,
                            latitude = locationDataEntity.latitude,
                        )
                    }
                }
        }
    }

    /**
     * This is where we would fetch data from a remote API or
     * get it from the local device.
     */
    override suspend fun fetchRemoteMapData() {
        withContext(dispatcher) {


        }
    }

    override fun getDeviceLocation(context: Context) {
        lateinit var fusedLocationProviderClient: FusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        // Check if location permissions are granted
        if (areLocationPermissionsGranted(context)) {
            // Retrieve the last known location
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener {
                    handleGetLastLocationSuccess(it)
                }
                .addOnFailureListener {
                    // Handle the failure case
                    print("Failed to get last location: \${it.message}")
                }
        }
    }



    fun areLocationPermissionsGranted(context: Context): Boolean {
        return (ActivityCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    context, Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED)
    }


}