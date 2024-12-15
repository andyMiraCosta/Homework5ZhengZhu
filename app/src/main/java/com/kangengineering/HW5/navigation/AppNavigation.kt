package com.kangengineering.HW5.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kangengineering.HW5.views.FavoritePetsScreen
import com.kangengineering.HW5.views.MapScreen
import com.kangengineering.HW5.views.PetDetailsScreen
import com.kangengineering.HW5.views.PetsScreen
import com.kangengineering.HW5.views.AdoptScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Composable
fun AppNavigation(
    contentType: ContentType,
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.PetsScreen.route
    ) {
        composable(Screens.PetsScreen.route) {
            PetsScreen(
                onPetClicked = { cat ->
                    navHostController.navigate(
                        "${Screens.PetDetailsScreen.route}/${Json.encodeToString(cat)}"
                    )
                },
                contentType = contentType
            )
        }
        composable(
            route = "${Screens.PetDetailsScreen.route}/{cat}",
            arguments = listOf(
                navArgument("cat") {
                    type = NavType.StringType
                }
            )
        ) {
            PetDetailsScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                },
                cat = Json.decodeFromString(it.arguments?.getString("cat") ?: "")
            )
        }
        composable(Screens.FavoritePetsScreen.route) {
            FavoritePetsScreen(
                onPetClicked = { cat ->
                    navHostController.navigate(
                        "${Screens.PetDetailsScreen.route}/${Json.encodeToString(cat)}"
                    )
                }
            )
        }

        composable(Screens.MapScreen.route) {
            MapScreen(

            )
        }

        composable(Screens.AdoptScreen.route) {
            AdoptScreen(

            )
        }

    }
}