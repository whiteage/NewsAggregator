package com.example.newsaggregator.ui

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

import com.example.newsaggregator.R
import com.example.newsaggregator.domain.NewsItem


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun newsCardForLazy(item : NewsItem, navHostController : NavHostController){
    val cardExtended = remember { mutableStateOf(false) }

    Card(
        modifier = if(cardExtended.value){Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .animateContentSize()
            .combinedClickable(
                onClick = { cardExtended.value = !cardExtended.value },
                onLongClick = { navHostController.navigate(Screens.WebView.createRoute(item.url))}
            )} else{
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .size(
                    height = 380.dp,
                    width = 100.dp
                )
                .animateContentSize()
                .combinedClickable(
                    onClick = { cardExtended.value = !cardExtended.value },
                    onLongClick = { navHostController.navigate(Screens.WebView.createRoute(item.url))}
                )
            },
        shape = RoundedCornerShape(25.dp),
        border = BorderStroke(2.dp, Color.Black),

    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = item.icon,
                contentDescription = "Guardian image",
                modifier = Modifier
                    .clip(RoundedCornerShape(25.dp))
                    .align(Alignment.CenterHorizontally)
                    .size(height = 200.dp, width = 400.dp)
                    .padding(bottom = 8.dp)
                ,
                contentScale = ContentScale.FillBounds,

            )


            Divider(color = Color.LightGray, thickness = 2.dp)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            ) {
                Text(
                    text = "${item.name} ",
                    modifier = Modifier.weight(1f),
                    color = Color.Black
                )

                Text(
                    text = "${item.date} ",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    color = Color.Black
                )
            }

            Divider(color = Color.LightGray, thickness = 2.dp)


            Text(
                text = "${item.desc}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            Divider(color = Color.LightGray   , thickness = 2.dp)


            Text(
                text = "${item.author}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 5.dp),
                color = Color.Black
            )
        }
    }
}