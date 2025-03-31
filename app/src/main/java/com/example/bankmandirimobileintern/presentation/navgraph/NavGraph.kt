package com.example.bankmandirimobileintern.presentation.navgraph

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.bankmandirimobileintern.presentation.news_navigator.components.NewsNavigator
import com.example.bankmandirimobileintern.presentation.onboarding.OnBoardingScreen
import com.example.bankmandirimobileintern.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onEvent = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route){
                NewsNavigator()
            }
        }
    }
}

//Kondisi login atau status aplikasi: Bisa jadi ada pengecekan status pengguna yang langsung menuju HomeScreen tanpa memeriksa apakah pengguna baru atau sudah melewati onboarding. Pastikan untuk memeriksa apakah status pengguna sudah diset untuk melalui onboarding.
//
//Pengaturan default: Pastikan bahwa pada aplikasi Anda tidak ada pengaturan default yang langsung mem-bypass onboarding. Misalnya, jika status pengguna disimpan dalam preferensi atau database lokal, pastikan pengaturan ini tidak langsung mengarah ke HomeScreen tanpa melewati proses onboarding.
//
//Masalah dengan Flow di NavGraph: Cek bagaimana NavGraph diatur. Biasanya, aplikasi menggunakan NavController untuk mendefinisikan urutan halaman yang akan dibuka. Jika alur ini tidak benar, itu bisa mengarahkan ke halaman yang salah.