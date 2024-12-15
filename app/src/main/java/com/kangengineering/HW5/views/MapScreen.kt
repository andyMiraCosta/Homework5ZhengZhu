package com.kangengineering.HW5.views




import android.Manifest
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kangengineering.HW5.data.PermissionAction
import com.kangengineering.HW5.viewmodel.MapViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MapScreen(
) {
    val mapViewModel: MapViewModel = koinViewModel()
    val mapUIState by mapViewModel.mapUIState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    var showContent by rememberSaveable { mutableStateOf(false) }

    PermissionDialog(
        context = context,
        permission = Manifest.permission.ACCESS_COARSE_LOCATION
    ) { permissionAction ->
        when (permissionAction) {
            is PermissionAction.PermissionDenied -> {
                showContent = false
            }

            is PermissionAction.PermissionGranted -> {
                showContent = true
                Toast.makeText(
                    context,
                    "Location permission granted!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    if (showContent) {
        mapViewModel.getDeviceLocation(context)
        MapScreenContent(
            context,
            modifier = Modifier
                .fillMaxSize(),

            mapUIState = mapUIState,
        )
    }


}