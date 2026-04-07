package com.example.eventologyvendor.Presentation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventologyvendor.R

@Composable
fun Login(){


    var selected by remember { mutableStateOf("Customer") }

    Box(modifier = Modifier.fillMaxSize()
        .background(color = colorResource( R.color.background)),
        contentAlignment = Alignment.Center
    ){
        Image(painter = painterResource(R.drawable.login),
            "loginBackground",
            modifier = Modifier.fillMaxSize().padding(bottom = 170.dp),
            alpha = 0.5f)

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
                .padding(top = 170.dp)) {
            ToggleUser(selected, onSelectedChange = {selected=it})
            Spacer(modifier= Modifier.height(60.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text(text = "Beautiful places \n to", modifier = Modifier.wrapContentWidth()
                    .wrapContentHeight().fillMaxWidth(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
                Text(text = "Book around you", modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight().fillMaxWidth(),
                    color = colorResource(R.color.primary),
                    fontWeight = FontWeight.Bold,
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier= Modifier.height(7.dp))
            Text(text = "Choose you dream destination,arena\n and much more for your event.", modifier = Modifier.fillMaxWidth()
                .wrapContentHeight().fillMaxWidth(),
                color = colorResource(R.color.textColor),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(50.dp))
            SignUpButton(selected)


            Spacer(Modifier.height(10.dp))
            ButtonWithLogo(selected,"Google", image = R.drawable.google)

            Spacer(Modifier.height(10.dp))
            ButtonWithLogo(selected,"Apple", image = R.drawable.apple)
        }
    }
}


@Composable
fun ToggleUser(
    selected: String,
    onSelectedChange: (String) -> Unit
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                colorResource(R.color.background1).copy(alpha = 0.6f),
                RoundedCornerShape(16.dp)
            )
            .padding(4.dp)
    ) {

        listOf("Customer", "Vendor").forEach { role ->

            val isSelected = selected == role

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        if (isSelected) colorResource(R.color.primary) else Color.Transparent
                    )
                    .clickable { onSelectedChange(role) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = role,
                    color = if (isSelected) Color.Black else Color.White.copy(0.7f),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun SignUpButton(role: String) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.primary))
            .clickable {

                if (role == "Customer") {
                    signUpCustomer()
                } else {
                    signUpVendor()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Sign up Free",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(Icons.Filled.ArrowForward,"Right Arrow")
        }
    }
}

@Composable
fun ButtonWithLogo(role: String,string:String,image:Int) {


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(width = 2.dp, color = colorResource(R.color.background1), shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.buttonBackground))
            .clickable {
                if (role == "Customer") {
                    signUpCustomer()
                } else {
                    signUpVendor()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center) {
            Icon(painter = painterResource(id=image) ,
                "",
                tint = Color.White,
                modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Continue with $string",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

        }
    }
}


fun signUpCustomer() {

}

fun signUpVendor() {


}

@Preview(showSystemUi = true)
@Composable
fun Show(){
    //ToggleUser()
    Login()
}
