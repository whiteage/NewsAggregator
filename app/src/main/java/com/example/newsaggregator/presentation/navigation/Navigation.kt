package com.example.newsaggregator.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsaggregator.presentation.ui.screen.MainScreen
import com.example.newsaggregator.presentation.ui.screen.ShareScreen
import com.example.newsaggregator.presentation.ui.screen.WebViewExample
import com.example.newsaggregator.presentation.viewmodel.MainVM


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

        composable(Screens.ShareScreen.route) {
            val encodedUrll = it.arguments?.getString("web")
            val urll = encodedUrll?.let { Uri.decode(it) }
            ShareScreen(viewModel,urll!!,navHostController)
        }
    }
}