package com.example.newsaggregator.ui

import android.net.Uri
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Navigation(viewModel: MainVM){
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = Screens.MainScreen.route){
        composable(Screens.MainScreen.route){
            MainScreen(viewModel, navHostController)
        }

        composable(Screens.WebView.route) {
            val encodedUrl = it.arguments?.getString("web")
            val url = encodedUrl?.let { Uri.decode(it) }
            WebViewExample(url!!)
        }
    }
}