package com.kangengineering.HW5.navigation

sealed class Screens(val route: String) {
    object PetsScreen : Screens("pets")
    object PetDetailsScreen : Screens("petDetails")
    object FavoritePetsScreen : Screens("favoritePets")
    object MapScreen : Screens("petMap")
    object AdoptScreen : Screens("adopt")
}