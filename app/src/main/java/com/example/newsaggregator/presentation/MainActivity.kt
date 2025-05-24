package com.example.newsaggregator.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.newsaggregator.presentation.navigation.Navigation
import com.example.newsaggregator.presentation.viewmodel.MainVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[MainVM::class.java]
        enableEdgeToEdge()
        setContent {
            Navigation(viewModel)

        }
    }
}
