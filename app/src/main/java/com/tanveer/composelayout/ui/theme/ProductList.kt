package com.tanveer.composelayout.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tanveer.composelayout.R
import com.tanveer.composelayout.product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductList() {

    val products = listOf(
        product("Nike Shoe", "$10,000", "*4.5(120 Reviews)", R.drawable.shoe1),
        product("Jordan Shoe", "$10,000", "*4.5(120 Reviews)", R.drawable.shoe2),
        product("Adiddas Shoe", "$10,000", "*4.5(120 Reviews)", R.drawable.shoe3),
        product("Puma Shoe", "$10,000", "*4.5(120 Reviews)", R.drawable.shoe4)
    )

    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val filteredProducts = products.filter {
        it.name.contains(query, ignoreCase = true)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBar(
                query = query,
                onQueryChange = { query = it },
                onSearch = { active = false },
                active = active,
                onActiveChange = { active = it },
                placeholder = { Text("Search") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {}

            Box{
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart",
                    modifier = Modifier
                        .size(36.dp)
                        .align(Alignment.TopEnd)
                )
                Text(
                    text = "0",
                    fontSize = 10.sp,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .background(Color.Red, shape = RoundedCornerShape(50))
                        .padding(horizontal = 5.dp, vertical = 2.dp)
                )
            }
        }
        // Product List
        LazyColumn {
            items(filteredProducts) { product ->
                ProductCard(
                    productName = product.name,
                    price = product.price,
                    rating = product.rating,
                    imageId = product.image
                )
            }
        }
    }
}

