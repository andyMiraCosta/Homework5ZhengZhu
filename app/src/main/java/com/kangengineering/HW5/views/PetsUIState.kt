package com.kangengineering.HW5.views

import com.kangengineering.HW5.data.Cat

data class PetsUIState(
    val isLoading: Boolean = false,
    val pets: List<Cat> = emptyList(),
    val error: String? = null
)
