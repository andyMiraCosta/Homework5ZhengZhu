package com.kangengineering.HW5.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kangengineering.HW5.viewmodel.PetsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AdoptScreen() {
    val petsViewModel: PetsViewModel = koinViewModel()

    Column(
    modifier = Modifier
    .fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "No favorite pets")
    }
}