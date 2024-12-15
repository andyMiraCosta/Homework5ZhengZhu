package com.kangengineering.HW5.navigation

sealed interface ContentType {
    object List : ContentType
    object ListAndDetail : ContentType
}