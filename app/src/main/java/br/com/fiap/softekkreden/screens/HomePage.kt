package br.com.fiap.softekkreden.screens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomePage(navController: NavController,
             ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home", modifier = Modifier.align(Alignment.CenterHorizontally).padding(0.dp,40.dp,0.dp), style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(30.dp))
        Row() {
            Button(
                onClick = { navController.navigate("questionScreen") },
                modifier = Modifier
                    .size(width = 150.dp, height = 50.dp),

                shape = CircleShape,
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),

            ) {
                Text("Avaliação")
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                onClick = { navController.navigate("dasboards") },
                modifier = Modifier
                    .size(width = 150.dp, height = 50.dp),

                shape = CircleShape,
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),

            ) {
                Text("Metricas")
            }
        }
    }
}