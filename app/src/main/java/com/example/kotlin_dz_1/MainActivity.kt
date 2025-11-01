package com.example.kotlin_dz_1

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            My_app()
        }
    }
}

@Composable
fun main_screen(
    navController: NavHostController
) {
    val orientation = LocalConfiguration.current.orientation
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    //var i =0
    val countState: MutableState<Int> = rememberSaveable { mutableStateOf(0) }
    //val list: MutableList<String> = MutableList(countState.value) { "Text ${i++}" }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.app_theme))
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                contentColor = colorResource(R.color.button),       // цвет текста
                containerColor = colorResource(R.color.button_color)
            ),
            modifier = Modifier
                .padding(0.dp, 0.dp, 10.dp, 10.dp)
                .align(Alignment.BottomEnd)
                .size(100.dp, 70.dp)
                .clip(
                    shape = RoundedCornerShape(15.dp)
                ),
            onClick = { countState.value += 1 }) {
            Text(
                text = "+",
                modifier = Modifier,
                fontSize = 25.sp,

                )
        }

        LazyVerticalGrid(
            columns = (if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                GridCells.Fixed(4)
            } else {
                GridCells.Fixed(3)
            }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 30.dp, 0.dp, 150.dp)
        ) {
            items(
                count = countState.value,
            ) { index ->
                Box(
                    modifier = Modifier

                        .size(
                            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                                screenWidth / 4
                            } else {
                                screenWidth / 3
                            }
                        )
                        .padding(5.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                10.dp
                            )
                        )
                        .background(
                            if (index % 2 == 0) {
                                colorResource(R.color.blueMy)
                            } else {
                                Color.Red
                            }
                        )
                        .clickable {
                            navController.navigate("detail/$index")
                        }

                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = index.toString(),
                    )
                }
            }
        }
    }
}

@Composable
fun block_screen(
    number: Int,
    isEven: Boolean
) {
    val backgroundColor =
        if (isEven) colorResource(R.color.blueMy) else colorResource(R.color.redMy)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)

    ) {
        Text(
            text = number.toString(),
            color = Color.White,
            fontSize = 60.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun My_app() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            main_screen(navController = navController)
        }
        composable("detail/{number}") { backStackEntry ->
            val number = backStackEntry.arguments?.getString("number")?.toIntOrNull() ?: 0
            val isEven = number % 2 == 0
            block_screen(number = number, isEven = isEven)
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true

)
@Composable
fun MyScreenPreview() {
    My_app()
}