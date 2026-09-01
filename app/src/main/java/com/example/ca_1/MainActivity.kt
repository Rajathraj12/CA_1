package com.example.ca_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ca_1.ui.theme.CA_1Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CA_1Theme {
                RestaurantMenu()
            }
        }
    }
}

class MenuItem(var name: String, var description: String, var price: String, var imageRes: Int)

@Composable
fun MenuList(
    items: List<MenuItem>,
    onItemClick: (MenuItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Restaurant Menu App", fontWeight = FontWeight.Bold, fontSize = 28.sp)
        Spacer(modifier = Modifier.height(24.dp))

        items.forEach { item ->
            Button(
                onClick = { onItemClick(item) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(text = item.name)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantMenu() {
    val menuItems = listOf(
        MenuItem("Burger", "Paneer Grilled Burger", "Rs 100", R.drawable.burger),
        MenuItem("Pizza", "Classic Margherita Pizza", "Rs 300", R.drawable.pizza),
        MenuItem("Pasta", "Creamy Alfredo sauce Pasta", "Rs 150", R.drawable.pasta)
    )

    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    var selectedItem by remember { mutableStateOf<MenuItem?>(null) }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                selectedItem?.let { item ->
                    Text(text = item.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = item.name,
                        modifier = Modifier
                            .size(150.dp)
                            .padding(8.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = item.description, fontSize = 16.sp)
                    Text(text = "Price: ${item.price}", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
        }
    ) { innerPadding ->
        MenuList(
            items = menuItems,
            onItemClick = { item ->
                selectedItem = item
                scope.launch { scaffoldState.bottomSheetState.expand() }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CA_1Theme {
        RestaurantMenu()
    }
}
