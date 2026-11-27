package com.example.mydatasiswa.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mydatasiswa.ui.EntrySiswaScreen
import com.example.mydatasiswa.ui.HomeScreen
import com.example.mydatasiswa.viewmodel.HomeViewModel
import com.example.mydatasiswa.viewmodel.PenyediaViewModel

private const val SISWA_DISIMPAN = "siswa_disimpan"

@Composable
fun DataSiswaApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    HostNavigasi(navController = navController, modifier = modifier)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier,
    ) {
        composable(DestinasiHome.route) { homeEntry ->
            val homeViewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
            LaunchedEffect(homeEntry, homeViewModel) {
                homeEntry.savedStateHandle.getStateFlow(SISWA_DISIMPAN, false).collect { saved ->
                    if (saved) {
                        homeEntry.savedStateHandle[SISWA_DISIMPAN] = false
                        homeViewModel.loadSiswa()
                    }
                }
            }
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntry.route) { launchSingleTop = true }
                },
                viewModel = homeViewModel,
            )
        }
        composable(DestinasiEntry.route) { entry ->
            EntrySiswaScreen(
                navigateBack = { navController.popBackStack() },
                onSaved = {
                    if (navController.currentBackStackEntry == entry) {
                        navController.previousBackStackEntry?.savedStateHandle?.set(SISWA_DISIMPAN, true)
                        navController.popBackStack()
                    }
                },
            )
        }
    }
}
