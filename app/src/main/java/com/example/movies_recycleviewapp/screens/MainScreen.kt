package com.example.movies_recycleviewapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainScreen(
    imageId: List<String>,
    names: List<String>,
    language: List<String>,
    navController: NavController,
    modifier: Modifier = Modifier)
{
    LazyColumn(contentPadding = PaddingValues(16.dp))
    {
        val itemCount = imageId.size
        items(itemCount){item ->
            ColumnItem(
                modifier,
                painter = imageId,
                title = names,
                language = language,
                itemIndex = item,
                navController =navController
            )
        }
    }



}

@Composable
fun ColumnItem(
    modifier: Modifier,
    painter: List<Int>,
    title: List<String>,
    language: List<String>,
    itemIndex: Int,
    navController: NavController
){
    Card(
        modifier
            .padding(10.dp)
            .wrapContentHeight()
            .clickable{
                navController.navigate(route = "DetailScreen/$itemIndex")
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White),
        elevation = CardDefaults.cardElevation(10.dp)
    )

    {
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(270.dp),
            //shape= CutCornerShape(20.dp)
            elevation = CardDefaults.cardElevation(10.dp),
            //border = BorderStroke(3.dp, Color.Gray)
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Column(modifier=Modifier.fillMaxSize()){
                Image(painter = painterResource(id = painter[itemIndex]), contentDescription=title[itemIndex], modifier.fillMaxWidth() . aspectRatio(16f/9f))
                Text(text=title[itemIndex],
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(10.dp)
                )

                Text(text=language[itemIndex],
                    fontSize = 18.sp,
                    modifier = Modifier.padding(6.dp),
                    maxLines=3,
                    overflow= TextOverflow.Ellipsis,
                    color = Color.Gray
                )
            }
        }
    }

}



