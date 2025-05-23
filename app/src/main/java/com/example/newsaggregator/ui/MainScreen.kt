package com.example.newsaggregator.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MainScreen(viewModel: MainVM, navHostController: NavHostController) {
    val news = viewModel.newsList.observeAsState(emptyList())
    val refreshing = viewModel.isRefreshing.observeAsState(initial = false)

    LaunchedEffect(Unit) {
        viewModel.getAllNews()
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing.value),
        onRefresh = { viewModel.refreshNews() }
    ) {
        if (news.value.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxSize()
            ) {
                itemsIndexed(items = news.value) { _, item ->
                    newsCardForLazy(item, navHostController)
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Loading...")
            }
        }
    }
}