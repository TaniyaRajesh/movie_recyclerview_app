package com.example.movies_recycleviewapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movies_recycleviewapp.screens.DetailScreen
import com.example.movies_recycleviewapp.screens.MainScreen
import com.example.movies_recycleviewapp.ui.theme.Movies_recycleViewAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Movies_recycleViewAppTheme {
                val navController = rememberNavController()

                val names = mutableStateOf<List<String>>(emptyList())
                val imageId = mutableStateOf<List<String>>(emptyList())
                val language = mutableStateOf<List<String>>(emptyList())

                fetchMovieData(
                    onSuccess = { fetchedNames, fetchedImageIds, fetchedlanguage ->
                        names.value = fetchedNames
                        imageId.value = fetchedImageIds
                        language.value = fetchedlanguage!!
                    },
                    onFailure = { error ->
                        Log.e("MainActivity", "Network error: ${error.message}")
                    }
                )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "MainScreen") {
                        composable(route = "MainScreen") {
                            MainScreen(
                                imageId = imageId.value,
                                names = names.value,
                                language = language.value,
                                navController = navController
                            )
                        }
                        composable(route = "DetailScreen/{index}", arguments = listOf(
                            navArgument(name = "index") { type = NavType.IntType }
                        )) { index ->
                            DetailScreen(
                                photos = imageId.value,
                                names = names.value,
                                language = language.value,
                                itemIndex = index.arguments?.getInt("index")
                            )
                        }
                    }
                }
            }
        }
    }
        fun fetchMovieData(onSuccess: (List<String>, List<String>, List<String>?) -> Unit, onFailure: (Throwable) -> Unit) {
            val retrofitBuilder = Retrofit.Builder()
                .baseUrl("https://api.tvmaze.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIInterface::class.java)
            val retroFitData = retrofitBuilder.getMovieData()

            retroFitData.enqueue(object : Callback<MyData?> {
                override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                    if(response.isSuccessful){
                        val responseBody = response.body()!!
                        val names = responseBody?.map { it.name } ?: emptyList()
                        val imageId = responseBody?.map { it.image.medium} ?: emptyList()
                        val language = responseBody?.map { it.language }
                        onSuccess(names, imageId, language)
                    }
                    else{
                        onFailure(Throwable("API error: ${response.code()}"))
                    }

                }

                override fun onFailure(call: Call<MyData?>, t: Throwable) {
                    onFailure(t)
                }
            })
        }

}




