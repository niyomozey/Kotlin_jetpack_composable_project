package com.example.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab2.ui.theme.Lab2Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
             Greeting()
        }
    }
}

@Composable
fun Greeting() {

    var name by remember { mutableStateOf("") }
    var urlId by remember { mutableStateOf(R.drawable.cute_animal)}
    var textField by remember { mutableStateOf("") }
    var CURRENT_ID by remember { mutableStateOf( 1)}
    var images = intArrayOf( R.drawable.cute_cat, R.drawable.cute_animal1, R.drawable.cute_animal, R.drawable.cute_pomeranian_dog, R.drawable.cute_nimal5, R.drawable.animal_bird)

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 40.dp)) {

        SetTextField(name = textField, changed = { textField = it })
        Button(onClick = {
            name = textField
            if( CURRENT_ID < images.size)
            {
                urlId = images[CURRENT_ID];
                CURRENT_ID++;
            }else
            {
                urlId = images[0];
                CURRENT_ID = 1;
            }
        }) { Text("Commit change"); }
        Image(painter = painterResource(id = urlId), contentDescription = stringResource(id  = R.string.searchName ),
            modifier = Modifier
                .padding(top = 40.dp, bottom = 40.dp)
                .size(190.dp)
                .clip(CircleShape)
        )
        Box(modifier = Modifier
            .width(280.dp)
            .height(40.dp)
            .background(Color.Gray)){
            Text( "You say: "+name,
                color = Color.Black,
                fontSize = 24.sp,
                textAlign = TextAlign.Center)
        }
    }
}
@Composable
fun SetTextField(name: String, changed : (String) ->Unit){
    TextField(
        value = name,
        label = { Text(stringResource(id = R.string.searchName)) },
        onValueChange = changed,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp)
    )
}
@Composable
fun changeProfileBtn(clicked: () -> Unit)
{
    Button(onClick = clicked) {Text("Commit change")}
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab2Theme {
        Greeting()
    }
}