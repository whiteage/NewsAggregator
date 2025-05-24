package com.example.newsaggregator.presentation.ui.screen

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.newsaggregator.presentation.navigation.Screens
import com.example.newsaggregator.presentation.viewmodel.MainVM

@Composable
fun ShareScreen(viewModel: MainVM, url : String, navHostController : NavHostController) {

    val content = viewModel.shareContent.observeAsState("")
    val shareLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        Log.d("Activity", "DEAD")
        navHostController.navigate(Screens.MainScreen.route)
    }


    content?.let { content ->

        LaunchedEffect(content) {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, url)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(intent, null)
            shareLauncher.launch(shareIntent)

        }
    }

}