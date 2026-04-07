package com.example.eventologyvendor.Presentation.Screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventologyvendor.R
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable     
fun seekBar() {
    var sliderPosition by remember { mutableStateOf(0f) }
    val sliderColors = SliderDefaults.colors(
        activeTrackColor = colorResource(R.color.activeColor),
        inactiveTrackColor = Color.LightGray,
        thumbColor = colorResource(R.color.border)
    )
    val points = listOf<Float>(0f, 1f, 2f)
    val listOfProgress = listOf<String>("Profile", "Cateory", "Verify")

    Column(modifier = Modifier.padding(16.dp)) {

        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..2f,
            track = { sliderState ->

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    SliderDefaults.Track(
                        sliderState = sliderState,
                        colors = sliderColors,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .height(10.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        @androidx.compose.runtime.Composable {
                            points.forEachIndexed { index, point ->
                                val selectedIndex = sliderPosition.roundToInt()
                                val isSelected = index == selectedIndex
                                Box(
                                    modifier = Modifier
                                        .size(14.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (isSelected)
                                                colorResource(R.color.activeColor)
                                            else
                                                Color.LightGray
                                        )
                                )
                            }
                        }
                    }
                }
            },
            thumb = {
                Box(
                    Modifier
                        .size(size = 40.dp)
                        .clip(CircleShape)
                        .background(colorResource(R.color.border))
                        .shadow(
                            shape = CircleShape,
                            elevation = 8.dp,
                            clip = false
                        )
                )
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 4.dp, end = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            points.forEachIndexed { index, point ->
                val isSelected = sliderPosition == point
                Text(
                    text = listOfProgress[index],
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) colorResource(R.color.activeColor) else Color.Gray
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun Shos() {
    seekBar()
}
