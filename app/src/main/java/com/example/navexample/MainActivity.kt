package com.example.navexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navexample.ui.theme.NavExampleTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

//sealed class Screen(val route: String) {
//    object Home: Screen("home")
//    object Greet: Screen("greet")
//}

enum class Screen {
    Home, Greet
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = Screen.Home.name) {
                        composable(Screen.Home.name) {
                            HomeScreen(navController = navController)
                        }
//                        composable(Screen.Greet.name) {
//                            GreetingScreen("Android",
//                                navController = navController
//                            )
                        composable("greet/world") {
                            GreetingScreen(name = "World", navController = navController)
                        }
                        composable("greet/android") {
                            GreetingScreen(name = "Android", navController = navController)
                        }
                        composable("greet/Friday") {
                            GreetingScreen(name = "Friday", navController = navController)
                        }
//                        composable("Screen.Greet.name/{name}",
//                            arguments = ListOf(navArgument("name") {
//                                type = NavType.StringType
//                            })) {
//                                val name = requireNotNull(it.arguments).getString("name")
//                                if(name != null)
//                                    GreetingScreen(name, navController = navController)
//                            }
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingScreen(name: String, navController: NavController, modifier: Modifier = Modifier) {
    Column (
        Modifier.fillMaxWidth().padding(),
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        Modifier.fillMaxWidth().padding(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Button(onClick = { navController.navigate("greet/world") }) {
            Text("World")
        }
        Button(onClick = { navController.navigate("greet/android") }) {
            Text("Android")
        }
        Button(onClick = { navController.navigate("greet/friday") }) {
            Text("Friday")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavExampleTheme {
        val navController = rememberNavController()
        HomeScreen(navController, modifier = Modifier)
    }
}