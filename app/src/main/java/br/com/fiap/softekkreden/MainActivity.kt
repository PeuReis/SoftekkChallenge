package br.com.fiap.softekkreden

import Questions
import Estatistic
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.softekkreden.screens.HomePage

import br.com.fiap.softekkreden.utils.GuidManager


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val guid = GuidManager.getOrCreateGuid(this)
        Log.d("APP_GUID", "Device GUID: $guid")
        setContent {
            val navController = rememberNavController()

            Scaffold { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .background(
                            color = br.com.fiap.softekkreden.ui.theme.Purple80,
                            shape = RectangleShape,

                            ),

                    horizontalAlignment = Alignment.CenterHorizontally,

                    )
                {


                    NavHost(navController = navController, startDestination = "home") {


                        composable(route = "home") {
                            HomePage(navController)
                        }
                        composable(route = "questionScreen") {
                            Questions(navController)
                        }
                        composable(route = "dashboards") {
                            Estatistic(navController)
                        }

                    }
                }
            }
        }
    }
}



//@Composable
//fun Greeting() {
//    PagAcompanhamento()
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    SofttekChallengeTheme {
//        PagAcompanhamento(navController)
//    }
//}