package com.example.newsaggregator.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsaggregator.R
import com.example.newsaggregator.presentation.viewmodel.MainVM

import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MainScreen(viewModel: MainVM, navHostController: NavHostController) {
    val news = viewModel.newsList.observeAsState(emptyList())
    val refreshing = viewModel.isRefreshing.observeAsState(initial = false)
    val categorys = viewModel.allCategorys.observeAsState(emptyList())
    val errorState = viewModel.errorState.observeAsState(false)

    LaunchedEffect(Unit) {
        viewModel.getAllNews()

    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing.value),
        onRefresh = { viewModel.refreshNews() }
    ) {
        if (news.value.isNotEmpty()) {
            Column {
            if(categorys.value.isNotEmpty()){
            LazyRow(modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(5.dp)) {itemsIndexed(items = categorys.value) { _, item ->
                Card(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    onClick = { viewModel.getNewsWithCategory(item) }
                ) {
                    Text(
                        text = item,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 16.sp
                    )
            } }}
            LazyColumn(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxSize()
            ) {
                itemsIndexed(items = news.value) { _, item ->
                    NewsCardForLazy(item, navHostController)
                }
            }}}
        } else {
            if (errorState.value == false){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(painterResource(R.drawable.guardianlogo), contentDescription = "guardian logo")
                }
            }
            else{
                ErrorScreen(onRetry = {viewModel.getAllNews()})

            }

        }
    }
}