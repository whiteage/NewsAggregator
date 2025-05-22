package com.example.newsaggregator.ui

import android.net.Uri

sealed class Screens(val route : String){
    object MainScreen : Screens("Main")
    object WebView : Screens("web_view/{web}") {
        fun createRoute(url: String) = "web_view/${Uri.encode(url)}"
    }


}