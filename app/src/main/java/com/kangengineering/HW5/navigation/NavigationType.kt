package com.kangengineering.HW5.navigation

sealed interface NavigationType {
    object BottomNavigation : NavigationType
    object NavigationDrawer : NavigationType
    object NavigationRail : NavigationType
}