package com.kangengineering.HW5.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kangengineering.HW5.data.MapRepository
import com.kangengineering.HW5.data.NetworkResult
import com.kangengineering.HW5.data.asResult
import com.kangengineering.HW5.views.MapUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MapViewModel(
    private val mapRepository: MapRepository
): ViewModel() {
    val mapUIState = MutableStateFlow(MapUIState())

    init {
        getMapData()
    }

    public fun getDeviceLocation(context: Context) {
        mapUIState.value = MapUIState(isLoading = true)
        mapRepository.getDeviceLocation(context)
    }

    private fun getMapData() {
        mapUIState.value = MapUIState(isLoading = true)
        viewModelScope.launch {
            mapRepository.getMapData().asResult().collect { result ->
                when (result ) {
                    is NetworkResult.Success -> {
                        mapUIState.update {
                            it.copy(isLoading = false, mapdata = result.data)
                        }
                    }

                    is NetworkResult.Error -> {
                        mapUIState.update {
                            it.copy(isLoading = false, error = result.error)
                        }
                    }
                }
            }
        }
    }
}