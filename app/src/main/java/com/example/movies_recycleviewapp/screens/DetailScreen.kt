package com.example.movies_recycleviewapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.remember
import com.google.accompanist.coil.rememberImagePainter

@Composable
fun DetailScreen(
    modifier: Modifier=Modifier,
    photos: List<String>,
    names: List<String>,
    language: List<String>,
    itemIndex: Int?
){
    Column(
        modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ){

        Box(modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = rememberImagePainter(photos[itemIndex!!]), // Use image URL here
                contentDescription = names[itemIndex],
                modifier.clip(RoundedCornerShape(16.dp))
            )
        }
            Text(text = names[itemIndex!!], fontSize= 30.sp, fontWeight = FontWeight.Bold)
            Text(text = language[itemIndex], fontSize= 18.sp)
    }
}

