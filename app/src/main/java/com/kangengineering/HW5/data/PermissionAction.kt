package com.kangengineering.HW5.data

sealed class PermissionAction {
    data object PermissionGranted : PermissionAction()
    data object PermissionDenied : PermissionAction()
}
