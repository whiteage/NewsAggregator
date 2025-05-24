package com.example.newsaggregator.presentation.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

import com.example.newsaggregator.domain.entity.NewsItem
import com.example.newsaggregator.presentation.navigation.Screens


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsCardForLazy(item: NewsItem, navHostController: NavHostController) {
    var cardExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .animateContentSize()
            .combinedClickable(
                onClick = { cardExpanded = !cardExpanded },
                onLongClick = { navHostController.navigate(Screens.WebView.createRoute(item.url)) }
            ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDFDFD))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            AsyncImage(
                model = item.icon,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = item.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = item.date,
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )

            if (cardExpanded) {
                Spacer(modifier = Modifier.height(8.dp))

                Divider(color = Color.LightGray, thickness = 1.dp)

                Text(
                    text = item.desc,
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "By ${item.author}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
