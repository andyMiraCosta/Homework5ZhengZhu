package com.kangengineering.HW5.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.kangengineering.HW5.navigation.Screens

@Composable
fun PetsBottomNavigationBar(
    onFavoriteClicked: () -> Unit,
    onHomeClicked: () -> Unit,
    onMapClicked: () -> Unit,
    onAdoptClicked: () -> Unit,
) {
    val items = listOf(Screens.PetsScreen, Screens.FavoritePetsScreen, Screens.MapScreen)
    val selectedItem = remember { mutableStateOf(items[0]) }
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        NavigationBarItem(
            selected = selectedItem.value == Screens.PetsScreen,
            onClick = {
                onHomeClicked()
                selectedItem.value = Screens.PetsScreen
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home Icon"
                )
            }
        )

        NavigationBarItem(
            selected = selectedItem.value == Screens.FavoritePetsScreen,
            onClick = {
                onFavoriteClicked()
                selectedItem.value = Screens.FavoritePetsScreen
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite Icon"
                )
            }
        )
        NavigationBarItem(
            selected = selectedItem.value == Screens.MapScreen,
            onClick = {
                onMapClicked()
                selectedItem.value = Screens.MapScreen
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Map Icon"
                )
            }
        )
        NavigationBarItem(
            selected = selectedItem.value == Screens.AdoptScreen,
            onClick = {
                onMapClicked()
                selectedItem.value = Screens.AdoptScreen
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Adopt Icon"
                )
            }
        )

    }
}