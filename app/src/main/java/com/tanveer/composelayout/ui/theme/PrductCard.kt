package com.tanveer.composelayout.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tanveer.composelayout.product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(
    productName: String,
    price: String,
    rating: String,
    imageId: Int
) {
    var count by rememberSaveable { mutableStateOf(0) }
    var isFavorite by rememberSaveable{ mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier
            .background(Color.White)
            .padding(16.dp)) {
            Image(
                painter = painterResource(id = imageId), contentDescription = productName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = productName, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = price, fontSize = 18.sp,color = Color(0xFFFF5722))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = rating, fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {

                Button(onClick = { if(count>0) count--}) {
                    Text(text = "-")
                }
                Text(text = count.toString(), fontSize = 14.sp, color = Color.Gray)
                Button(onClick = { count++ }) {
                    Text(text = "+")
                }
            }
            IconButton(onClick = { isFavorite = !isFavorite }) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }
           Button(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.End)) {
               Text("Add To cart")
           }
        }
    }
}