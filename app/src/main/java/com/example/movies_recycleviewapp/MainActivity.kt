package com.example.movies_recycleviewapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movies_recycleviewapp.ui.theme.Movies_recycleViewAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Movies_recycleViewAppTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val imageId = arrayOf(
                        R.drawable.p1,
                        R.drawable.p2,
                        R.drawable.p3,
                        R.drawable.p4,
                        R.drawable.p5,
                        R.drawable.p6
                    )

                    val names = arrayOf(
                        "Peperoni",
                        "Vegan",
                        "FourCheese",
                        "Margaritta",
                        "American",
                        "Mexican"
                    )

                    val ingredients = arrayOf(
                        "Tomato sos, cheese, oregano, peperoni",
                        "Tomato sos, cheese, oregano, spinach, green paprika, rukola",
                        "Tomato sos, oregano, mozzarella, goda, parmesan, cheddar",
                        "Tomato sos, cheese, oregano, bazillion",
                        "Tomato sos, cheese, oregano, green paprika, red beans",
                        "Tomato sos, cheese, oregano, corn, jalapeno, chicken"
                    )

                    MyApp(imageId,names,ingredients)
                }
            }
        }
    }
}

@Composable
fun MyApp(imageId: Array<Int>,
          names: Array<String>,
          ingredients: Array<String>,
          modifier: Modifier = Modifier)
    {
        LazyColumn(contentPadding = PaddingValues(16.dp))
        {
            val itemCount = imageId.size
            items(itemCount){item ->
                ColumnItem(
                    itemIndex = item,
                    painter = imageId,
                    title = names,
                    ingredients = ingredients,
                    modifier
                )
            }
        }



}

@Composable
fun ColumnItem(  itemIndex: Int,
                 painter: Array<Int>,
                 title: Array<String>,
                 ingredients: Array<String>,
                 modifier: Modifier){
    Card(
        modifier
            .padding(10.dp)
            .wrapContentHeight(),
            colors = CardDefaults.cardColors(
            containerColor = Color.White),
            elevation = CardDefaults.cardElevation(10.dp)
    )//{
        //Box(modifier=Modifier.fillMaxSize(),
           // contentAlignment = Alignment.Center
        //)
        {
            Card(modifier = Modifier
                .width(200.dp)
                .height(270.dp),
                //shape= CutCornerShape(20.dp)
                elevation = CardDefaults.cardElevation(10.dp),
                //border = BorderStroke(3.dp, Color.Gray)
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ){
                Column(modifier=Modifier.fillMaxSize()){
                    Image(painter = painterResource(id = painter[itemIndex]), contentDescription=title[itemIndex], modifier.size(140.dp))
                    Text(text=title[itemIndex],
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(10.dp)
                    )

                    Text(text=ingredients[itemIndex],
                        fontSize = 18.sp,
                        modifier = Modifier.padding(6.dp),
                        maxLines=3,
                        overflow= TextOverflow.Ellipsis,
                        color = Color.Gray
                    )
                }
            }
        //}
    }



}



