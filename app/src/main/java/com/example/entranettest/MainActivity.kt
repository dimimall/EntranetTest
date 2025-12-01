package com.example.entranettest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.entranettest.JsonModels.User
import com.example.entranettest.Network.CartUiState
import com.example.entranettest.Network.UsersUiState
import com.example.entranettest.ViewModel.CartViewModel
import com.example.entranettest.ViewModel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "users"
            ) {
                composable("users") {
                    UsersScreen(
                        onUserClick = { user ->
                            // navigate to detail, passing the id
                            navController.navigate("userDetail/${user.id}/${user.firstName}/${user.lastName}")
                        }
                    )
                }

                composable(
                    route = "userDetail/{userId}/{firstName}/{lastName}",
                    arguments = listOf(
                        navArgument("userId") { type = NavType.IntType },
                        navArgument("firstName") { type = NavType.StringType },
                        navArgument("lastName") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getInt("userId")!!
                    val firstName = backStackEntry.arguments?.getString("firstName")!!
                    val lastName = backStackEntry.arguments?.getString("lastName")!!
                    UserDetailScreen(userId = userId, firstName = firstName, lastName = lastName,
                        onBack = { navController.popBackStack() })
                }
            }
        }
    }
}

@Composable
fun UsersScreen(
    viewModel: UsersViewModel = hiltViewModel(),
    onUserClick: (User) -> Unit
)
{

    val state by viewModel.usersState.observeAsState(UsersUiState.Loading)
    when (state) {
        is UsersUiState.Loading -> LoadingView()
        is UsersUiState.Success -> UserList((state as UsersUiState.Success).data.users, onUserClick = onUserClick)
        is UsersUiState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Error: ${(state as UsersUiState.Error).message}")
                Spacer(Modifier.height(8.dp))
                Button(onClick = { viewModel.loadUsers() }) {
                    Text("Retry")
                }
            }
        }
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}

@Composable
fun UserList(users: List<User>, onUserClick: (User) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(vertical = 36.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(users){
            user -> UserItem(user,
            onClick = {
                onUserClick(user)
            })
        }
    }
}

@Composable
fun UserItem(user: User,onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },  // 👈 this makes the whole card clickable
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("${user.firstName} ${user.lastName}", fontSize = 20.sp)
            Text(user.email, fontSize = 14.sp)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(userId: Int, firstName:String, lastName:String, onBack: () -> Unit,  viewModel: CartViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column (modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            val state by viewModel.cartState.observeAsState(CartUiState.Loading)
            when (state) {
                is CartUiState.Loading -> LoadingView()
                is CartUiState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Error: ${(state as CartUiState.Error).message}")
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { viewModel.loadCarts() }) {
                            Text("Retry")
                        }
                    }
                }
                is CartUiState.Success -> {
                    val carts = (state as CartUiState.Success).data.carts
                    //val cart = carts.find { it.userId == userId }
                    for (cart in carts) {
                        if (cart.id == userId) {
                            Text("User ID = $userId")
                            Spacer(Modifier.height(50.dp))
                            Text("First Name = $firstName")
                            Spacer(Modifier.height(50.dp))
                            Text("Last Name = $lastName")
                            Spacer(Modifier.height(50.dp))
                            Text("Total = ${cart.total}")
                            Spacer(Modifier.height(50.dp))
                            Text("Discounted Total = ${cart.discountedTotal}")
                            Spacer(Modifier.height(50.dp))
                            Text("Total Products = ${cart.totalProducts}")
                        }

                    }
                }
            }
        }
    }
}
