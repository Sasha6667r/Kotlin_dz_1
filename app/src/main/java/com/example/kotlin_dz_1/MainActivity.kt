package com.example.kotlin_dz_1

import android.content.res.Configuration
import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.benchmark.traceprocessor.Row
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin_dz_1.ui.theme.Kotlin_dz_1Theme

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
fun My_app(

) {
    val orientation = LocalConfiguration.current.orientation
    var i =remember{0}
    val countState: MutableState<Int> = rememberSaveable{ mutableStateOf(0) }
    val list: MutableList<String> = MutableList(countState.value) { "Text ${i++}" }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xff004D40),       // цвет текста
                containerColor = Color(0xff9ed6df)
            ),
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomEnd)
                .clip(
                    shape = RoundedCornerShape(15.dp)
                ),
            onClick = { countState.value+=1 }) {
            Text(
                text = "+",
                modifier = Modifier,
                fontSize = 25.sp

            )
        }

        LazyVerticalGrid(
        columns = (if(orientation == Configuration.ORIENTATION_LANDSCAPE){GridCells.Fixed(4)}else{GridCells.Fixed(3)}),
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp,30.dp,0.dp,150.dp)
    ) {
        items(
            count =list.size,
        ){ index ->
            when(index) {
                in 1..list.size step 2 ->
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(5.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                10.dp
                            )
                        )
                        .background(Color.Blue)

                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = list[index],
                    )
                }
                in 0..list.size step 2 ->
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(5.dp)
                            .clip(
                                shape = RoundedCornerShape(
                                    10.dp
                                )
                            )
                            .background(Color.Red)

                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center),
                            text = list[index],
                        )
                    }
            }
        }
    }
    }
}
//поработать с ресурсами


@Preview(
    showBackground = true,
    showSystemUi = true

)
@Composable
fun MyScreenPreview() {
    My_app()
}