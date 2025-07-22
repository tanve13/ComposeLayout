package com.tanveer.composelayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tanveer.composelayout.ui.theme.ComposeLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    welcomeMessage(modifier = Modifier)
//                    showSum(a = 2, b = 4, modifier = Modifier)
//                    interactiveSum()
                    mainScreen()
                }

            }
        }
    }
}
@Composable
fun welcomeMessage(modifier: Modifier){
    Text(text = "Hello Tanveer",
    modifier = modifier )
}

@Composable
fun showSum(a:Int,b:Int,modifier: Modifier = Modifier){
    val sum = a+b
    Text(text = "Sum of $a and $b is $sum", modifier = modifier
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun interactiveSum(){
    val number1 = remember {mutableStateOf("") }
    val number2 = remember {mutableStateOf("") }
    val result = remember{ mutableStateOf<Int?>(null) }
    val errorMessage = remember { mutableStateOf<String?>(null) }

    //Layout design krna hai ab
    Column(modifier = Modifier.padding(16.dp)){
        TextField(
            value = number1.value,
            onValueChange = { number1.value = it
                errorMessage.value = null },
            label = { Text(text = "Enter First Number:") }
        )
        TextField(
            value = number2.value,
            onValueChange = { number2.value = it
                errorMessage.value = null },
            label = { Text(text = "Enter Second Number:") }
        )
        Button(onClick = {
            val a = number1.value.toIntOrNull()
            val b = number2.value.toIntOrNull()
            if(a != null && b != null){
                result.value = a+b
                errorMessage.value = null
            }else{
                result.value = null
                errorMessage.value = "please enter valid number"
            }
        }) {
Text("Sum")
        }
        //  Error message
        errorMessage.value?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        //result display krna if result is valid
        result.value?.let {
            Text(text = "Result: $it")
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mainScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Main Screen")
                },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Handle menu click */ }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Handle settings click */ }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        // Your main screen content here
        Text(
            text = "This is my App",
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeLayoutTheme {
//        welcomeMessage(modifier = Modifier)
//        showSum(a = 2, b = 4, modifier = Modifier)
//        interactiveSum()
        mainScreen()
    }
}