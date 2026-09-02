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

class RestaurantMenuApp_CA1 : ComponentActivity() {
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

@Composable
fun MenuList(
    onBurgerClick: () -> Unit,
    onPizzaClick: () -> Unit,
    onPastaClick: () -> Unit,
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

        Button(onClick = onBurgerClick, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Text(text = "Burger")
        }
        Button(onClick = onPizzaClick, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Text(text = "Pizza")
        }
        Button(onClick = onPastaClick, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Text(text = "Pasta")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantMenu() {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    var burger by remember { mutableStateOf(false) }
    var pizza by remember { mutableStateOf(false) }
    var pasta by remember { mutableStateOf(false) }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            if (burger) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Burger", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.burger),
                        contentDescription = "Burger",
                        modifier = Modifier.size(150.dp).padding(8.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "Paneer Grilled Burger", fontSize = 16.sp)
                    Text(text = "Price: Rs 100", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
            if (pizza) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Pizza", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.pizza),
                        contentDescription = "Pizza",
                        modifier = Modifier.size(150.dp).padding(8.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "Classic Margherita Pizza", fontSize = 16.sp)
                    Text(text = "Price: Rs 300", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
            if (pasta) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Pasta", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.pasta),
                        contentDescription = "Pasta",
                        modifier = Modifier.size(150.dp).padding(8.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "Creamy Alfredo sauce Pasta", fontSize = 16.sp)
                    Text(text = "Price: Rs 150", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
        }
    ) { innerPadding ->
        MenuList(
            onBurgerClick = {
                burger = true; pizza = false; pasta = false
                scope.launch { scaffoldState.bottomSheetState.expand() }
            },
            onPizzaClick = {
                pizza = true; burger = false; pasta = false
                scope.launch { scaffoldState.bottomSheetState.expand() }
            },
            onPastaClick = {
                pasta = true; burger = false; pizza = false
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
