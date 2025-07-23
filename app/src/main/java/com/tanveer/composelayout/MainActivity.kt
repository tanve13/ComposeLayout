package com.tanveer.composelayout

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
//                    mainScreen()
                    SignUpScreen()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen() {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Select Gender") }

    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }

    var errors by remember {
        mutableStateOf(
            mapOf<String, String?>()
        )
    }

    val genderOptions = listOf("Male", "Female", "Other")
    var genderExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Sign Up", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        // Full Name
        OutlinedTextField(
            value = fullName,
            onValueChange = {
                fullName = it
                errors = errors - "fullName"
            },
            label = { Text("Full Name") },
            isError = errors["fullName"] != null,
            modifier = Modifier.fillMaxWidth()
        )
        errors["fullName"]?.let {
            Text(it, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                errors = errors - "email"
            },
            label = { Text("Email") },
            isError = errors["email"] != null,
            modifier = Modifier.fillMaxWidth()
        )
        errors["email"]?.let {
            Text(it, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Password
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                errors = errors - "password"
            },
            label = { Text("Password") },
            isError = errors["password"] != null,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(imageVector = icon, contentDescription = "Toggle Password")
                }
            }
        )
        errors["password"]?.let {
            Text(it, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Confirm Password
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                errors = errors - "confirmPassword"
            },
            label = { Text("Confirm Password") },
            isError = errors["confirmPassword"] != null,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (showConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (showConfirmPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(onClick = { showConfirmPassword = !showConfirmPassword }) {
                    Icon(imageVector = icon, contentDescription = "Toggle Confirm Password")
                }
            }
        )
        errors["confirmPassword"]?.let {
            Text(it, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Phone
        OutlinedTextField(
            value = phone,
            onValueChange = {
                phone = it
                errors = errors - "phone"
            },
            label = { Text("Phone") },
            isError = errors["phone"] != null,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )
        errors["phone"]?.let {
            Text(it, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Gender Dropdown
        ExposedDropdownMenuBox(
            expanded = genderExpanded,
            onExpandedChange = { genderExpanded = !genderExpanded }
        ) {
            OutlinedTextField(
                value = gender,
                onValueChange = {},
                readOnly = true,
                label = { Text("Gender") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = genderExpanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = genderExpanded,
                onDismissRequest = { genderExpanded = false }
            ) {
                genderOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            gender = option
                            genderExpanded = false
                            errors = errors - "gender"
                        }
                    )
                }
            }
        }

        errors["gender"]?.let {
            Text(it, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Submit Button
        Button(
            onClick = {
                val newErrors = mutableMapOf<String, String?>()

                if (fullName.isBlank()) newErrors["fullName"] = "Name is required"
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) newErrors["email"] = "Invalid email"
                if (password.length < 6) newErrors["password"] = "Password must be at least 6 characters"
                if (password != confirmPassword) newErrors["confirmPassword"] = "Passwords do not match"
                if (phone.length != 10) newErrors["phone"] = "Invalid phone number"
                if (gender == "Select Gender") newErrors["gender"] = "Please select gender"

                if (newErrors.isEmpty()) {
                    println("Submitted: $fullName, $email, $phone, $gender")
                }

                errors = newErrors
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign Up")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeLayoutTheme {
//        welcomeMessage(modifier = Modifier)
//        showSum(a = 2, b = 4, modifier = Modifier)
//        interactiveSum()
//        mainScreen()
        SignUpScreen()
    }
}