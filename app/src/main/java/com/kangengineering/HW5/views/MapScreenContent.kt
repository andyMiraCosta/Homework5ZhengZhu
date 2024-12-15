package com.kangengineering.HW5.views



import android.content.Context
import android.location.Location
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.kangengineering.HW5.data.LocationData

@Composable
fun MapScreenContent(
    context: Context,
    modifier: Modifier,
    mapUIState: MapUIState,
) {
    AnimatedVisibility(
        visible = mapUIState.isLoading
    ) {
        CircularProgressIndicator()
    }

    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GoogleMapScreen(context, mapUIState)
    }
}

fun handleGetLastLocationSuccess(location: Location?) {
    // Handle the success case with the location parameter
    print("Location using LAST-LOCATION: LATITUDE: \${location.latitude}, LONGITUDE: \${location.longitude}")
}

@Composable
fun GoogleMapScreen(context: Context, mapUIState: MapUIState) {
    var locationText by remember { mutableStateOf("No location obtained :(") }
    var showPermissionResultText by remember { mutableStateOf(false) }
    var permissionResultText by remember { mutableStateOf("Permission Granted...") }
    var locationData: LocationData? = null

    if (mapUIState.mapdata.isNotEmpty()){
        locationData = mapUIState.mapdata[0]
    }
    var atasehir = LatLng(40.9971, 29.1007)
    if (locationData != null) {
        var latitude: Double? = locationData.latitude.toDoubleOrNull()
        var longitude: Double? = locationData.longitude.toDoubleOrNull()
        if (latitude == null) {
            latitude = 40.9971
        }
        if (longitude == null) {
            longitude = 29.1007
        }
        atasehir = LatLng(latitude!!, longitude!!)
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(atasehir, 15f)
    }


    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    )
}


